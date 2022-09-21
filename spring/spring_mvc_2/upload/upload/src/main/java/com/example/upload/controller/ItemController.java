package com.example.upload.controller;

import com.example.upload.domain.Item;
import com.example.upload.domain.ItemRepository;
import com.example.upload.domain.UploadFile;
import com.example.upload.file.FileStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;
    private final FileStore fileStore;

    @GetMapping("/items/new")
    public String newItem(@ModelAttribute ItemForm form) {
        return "item-form";
    }

    @PostMapping("/items/new")
    public String saveItem(@ModelAttribute ItemForm form, RedirectAttributes redirectAttributes) throws IOException {
        UploadFile attachFile = fileStore.storeFile(form.getAttachFile());

        List<UploadFile> storeImageFiles = fileStore.storeFiles(form.getImageFiles());

        // 데이터베이스에 저장
        Item item = new Item();
        item.setItemName(form.getItemName());
        item.setAttachFile(attachFile);
        item.setImageFiles(storeImageFiles);
        itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", item.getId());

        return "redirect:/items/{itemId}";
    }

    @GetMapping("/items/{id}")
    public String items(@PathVariable Long id, Model model) {
        Item item = itemRepository.findById(id);
        model.addAttribute("item", item);
        return "item-view";
    }

    //    @ResponseBody
//    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file" + fileStore.getFullPath(filename));
    }

    // V1.png 이미지 전용  : 참고자료 : https://lazymankook.tistory.com/74
//    @ResponseBody
//    @GetMapping(value = "/images/{filename}", produces = MediaType.IMAGE_PNG_VALUE)
    public Resource downloadImageV1(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileStore.getFullPath(filename));
    }

    // 모든 Content-type 사용
    @GetMapping("/images/{filename}")
    public ResponseEntity<Resource> downloadImageV2(@PathVariable String filename) throws IOException {
        String fullPath = fileStore.getFullPath(filename);
        MediaType mediaType = MediaType.parseMediaType(Files.probeContentType(Paths.get(fullPath)));
        UrlResource resource = new UrlResource("file:" + fullPath);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, mediaType.toString())
                .body(resource);
    }

    @GetMapping("/attach/{itemId}")
    public ResponseEntity<Resource> downloadAttach(@PathVariable Long itemId) throws MalformedURLException {
        Item item = itemRepository.findById(itemId);
        String storeFileName = item.getAttachFile().getStoreFileName();
        String uploadFileName = item.getAttachFile().getUploadFileName();
        UrlResource resource = new UrlResource("file:" + fileStore.getFullPath(storeFileName));

        log.info("uploadFileName={}", uploadFileName);

        String encodedUploadFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);

        // contentDisposition 을 넣어주지 않으면 브라우저에서 다운로드 되지 않고 바로 화면에 보여준다.
//        String contentDisposition = "attachment; filename= \"" + uploadFileName + "\"";

        // 파일명 인코딩 하기 (예전 브라우저의 경우 한글이 깨질 수 있으므로)
        String contentDisposition = "attachment; filename= \"" + encodedUploadFileName + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(resource);
    }
}
