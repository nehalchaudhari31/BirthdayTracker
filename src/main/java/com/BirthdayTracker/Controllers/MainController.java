package com.BirthdayTracker.Controllers;

import java.awt.PageAttributes.MediaType;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.util.StreamUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.BirthdayTracker.Dao.IUserDao;
import com.BirthdayTracker.Entities.User;
import com.BirthdayTracker.Entities.UserImageFile;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
public class MainController {
	@Autowired
	private IUserDao userdao;
	@Autowired
	private ResourceLoader resourceLoader;

	@Value("${project.image}")
	String path;

	@GetMapping("/all-users")
	public List<User> AllUsers() {
		List<User> allusers = userdao.findAll();
		// System.out.println("AllUsers"+allusers.get(0).getId());
		return allusers;
	}

	@PostMapping("/add-user")
	public User AddUser(@ModelAttribute User user, @RequestParam("image") MultipartFile file) {
		User saveduser = null;
		System.out.println("AddUser");
//		Saving profile image file;
		try {
			String name = file.getOriginalFilename();
			String filePath = path + File.separator + name;
			// Creating folder if not exists
			File f = new File(path);
			if (!f.exists()) {
				f.mkdir();
			}
			// System.out.println("File Path "+filePath);
			Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
			user.setProfilePicName(file.getOriginalFilename());
			saveduser = userdao.save(user);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return saveduser;
	}

	@GetMapping(value = "/profileimage/{profilepic}", produces = org.springframework.http.MediaType.IMAGE_PNG_VALUE)
	public void GetImage(@PathVariable("profilepic") String profilepic, HttpServletResponse response)
			throws IOException {
		String fullpath = path + File.separator + profilepic;  
		
		System.out.println("Path:- "+path);
		System.out.println("ProfilePic:- "+profilepic);
	
		InputStream is = new FileInputStream(fullpath);
		response.setContentType(org.springframework.http.MediaType.IMAGE_PNG_VALUE);
		org.springframework.util.StreamUtils.copy(is, response.getOutputStream());
	}

	@GetMapping(value = ("/get-user/{userid}"))
	public ResponseEntity<?> GetUser(@PathVariable("userid") String userid) { 
		Optional<User> u=userdao.findById(Integer.valueOf(userid));
		if(u.isEmpty()) {
			return new ResponseEntity("No user found for id "+userid,HttpStatus.NOT_FOUND);
		}
		else {
			//System.out.println(u.get().getProfilePicName());
			return new ResponseEntity<Optional<User>>(u,HttpStatus.FOUND);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Optional<User>> Deleteuser(@PathVariable("id") int id) {

		Optional<User> u = userdao.findById(id);
		if (u.isEmpty()) {
			return new ResponseEntity("No User found for id " + id, HttpStatus.NOT_FOUND);
		} else {

			userdao.deleteById(id);
			return new ResponseEntity<Optional<User>>(u, HttpStatus.OK);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<User> UpdateUser(@PathVariable("id") int id, @RequestBody User user) {  
		User updatedUser = userdao.save(user);
		return new ResponseEntity<User>(updatedUser, HttpStatus.OK); 
	}
}
