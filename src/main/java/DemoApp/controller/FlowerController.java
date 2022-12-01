package DemoApp.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.activation.FileTypeMap;
import javax.imageio.ImageIO;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import DemoApp.model.InventoryProductImage;
import DemoApp.service.FlowerService;
import DemoApp.storage.StorageService;
import Helpers.Extensions;
import Helpers.MappingHelper;
import Request.FlowerRequest;
import Response.ResponseObject;

@RestController
@CrossOrigin("*")
public class FlowerController {
	@Autowired
	private FlowerService flowerService;

	@Autowired
	private StorageService storageService;

//	@Autowired
//	public FlowerController(StorageService storageService) {
//		this.storageService = storageService;
//	}

	@GetMapping(path = "/flower/get")
	public ResponseEntity<Object> getAllFlower() throws SQLException, ClassNotFoundException {
		ResponseObject<Object> apiRespo = new ResponseObject<Object>();
		try {
			apiRespo.setObject(flowerService.getAllFlower());
			return ResponseEntity.ok(apiRespo);
		} catch (Exception e) {
			return Extensions.catchApplicationError(e, apiRespo, e.getMessage());
		}
		
	}

	@PostMapping(path = "/flower/get-by-id")
	public ResponseEntity<Object> getFlowerById(@RequestBody FlowerRequest request)
			throws SQLException, ClassNotFoundException {
		ResponseObject<Object> apiRespo = new ResponseObject<Object>();
		try {
			
			apiRespo.setObject(flowerService.getFlowerById(request));
			return ResponseEntity.ok(apiRespo);
		} catch (Exception e) {
			return Extensions.catchApplicationError(e, apiRespo, e.getMessage());
		}
	}

	@GetMapping(path = "/flower/get-by-key")
	public ResponseEntity<Object> getFlowerByKey(@RequestBody FlowerRequest request)
			throws SQLException, ClassNotFoundException {
		ResponseObject<Object> apiRespo = new ResponseObject<Object>();
		
try {
			
			apiRespo.setObject(flowerService.getFlowerByKey(request));
			return ResponseEntity.ok(apiRespo);
		} catch (Exception e) {
			return Extensions.catchApplicationError(e, apiRespo, e.getMessage());
		}
	}

	@PostMapping("flower/add-flower")
	public ResponseEntity<Object> addFlower(@RequestParam("files") MultipartFile[] files,
			@ModelAttribute FlowerRequest request) {
		ResponseObject<Object> apiRespo = new ResponseObject<Object>();
		try {
			List<String> lstImage = new ArrayList<String>();
			for (MultipartFile file : files) {
				storageService.store(file);
				lstImage.add(file.getOriginalFilename());
			}
			request.setListImage(String.join(",", lstImage));				
			flowerService.addFlower(request);
			apiRespo.setObject("Thêm mới sản phẩm thành công!");
			return ResponseEntity.ok(apiRespo);
		} catch (Exception e) {
			return Extensions.catchApplicationError(e, apiRespo, e.getMessage());

		}
	}

	@PostMapping("flower/update-flower")
	public ResponseEntity<Object> updateFlower(@RequestParam("file") MultipartFile file,
			@ModelAttribute FlowerRequest request) {
		ResponseObject<Object> apiRespo = new ResponseObject<Object>();
		try {
//			storageService.store(file);
//			request.setListImage(file.getOriginalFilename());
			flowerService.updateFlower(request);
			apiRespo.setObject("Cập nhật thông tin sản phẩm thành công!");
			return ResponseEntity.ok(apiRespo);
		} catch (Exception e) {
			return Extensions.catchApplicationError(e, apiRespo, e.getMessage());

		}
	}

	@PostMapping("flower/delete-flower")
	public ResponseEntity<Object> deleteFlower(@RequestBody FlowerRequest request) {
		ResponseObject<Object> apiRespo = new ResponseObject<Object>();
		try {

			flowerService.deleteFlower(request);
			apiRespo.setObject("Xóa sản phẩm thành công!");
			return ResponseEntity.ok(apiRespo);
		} catch (Exception e) {
			return Extensions.catchApplicationError(e, apiRespo, e.getMessage());

		}
	}

	@GetMapping(value = "/image/{filename:.+}", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public ResponseEntity<byte[]> serveFile(@PathVariable String filename) throws IOException {

		// String filePath = storageService.getPath(filename);
		File imgFile = storageService.loadFile(filename);
		return ResponseEntity.ok().header("Content-Disposition", "attachment; filename=" + imgFile.getName())
				.contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(imgFile)))
				.body(Files.readAllBytes(imgFile.toPath()));
	}


	



}
