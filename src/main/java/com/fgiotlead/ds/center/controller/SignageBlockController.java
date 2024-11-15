package com.fgiotlead.ds.center.controller;//package com.fgiotlead.ds.center.controller;
//
//import com.fgiotlead.ds.center.model.service.SignageBlockService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/signage/block")
//public class SignageBlockController {
//
//    @Autowired
//    private SignageBlockService signageBlockService;
//
////    @GetMapping("")
////    public List<SignageBlockEntity> getBlockList() {
////        return signageBlockService.findAll();
////    }
//
////    @GetMapping("/{blockId}")
////    @Operation(summary = "查詢特定播放區", description = "使用 ID 查詢播放區")
////    public ResponseEntity<SignageBlockEntity> getBlockById(
////            @PathVariable(name = "blockId") UUID id
////    ) {
////        return signageBlockService.findById(id)
////                .map(signageBlockEntity -> new ResponseEntity<>(signageBlockEntity, HttpStatus.OK))
////                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
////    }
////
////    @PostMapping("")
////    @Operation(summary = "修改指定播放區")
////    public ResponseEntity<Map<String, String>> saveBlock(
////            @RequestBody SignageBlockEntity signageBlock
////    ) {
////        Map<String, String> responseEntity = new HashMap<>();
////        if (signageBlock.getName() != null && signageBlock.getId() != null) {
////            Optional<SignageBlockEntity> existSignageBlock = signageBlockService.findById(signageBlock.getId());
////            if (existSignageBlock.isPresent()) {
////                signageBlock.setStyle(existSignageBlock.get().getStyle());
////                signageBlockService.save(signageBlock);
////                responseEntity.put("message", "修改成功");
////                return new ResponseEntity<>(responseEntity, HttpStatus.OK);
////            } else {
////                responseEntity.put("message", "修改失敗");
////                responseEntity.put("state", "播放區不存在");
////                return new ResponseEntity<>(responseEntity, HttpStatus.NOT_FOUND);
////            }
////        } else {
////            responseEntity.put("message", "修改失敗");
////            return new ResponseEntity<>(responseEntity, HttpStatus.BAD_REQUEST);
////        }
////    }
//
////    @DeleteMapping("/{blockId}")
////    public ResponseEntity<Map<String, String>> deleteById(@PathVariable(name = "blockId") UUID id) {
////        Map<String, String> responseEntity = new HashMap<>();
////        Optional<SignageBlockEntity> signageBlock = signageBlockService.findById(id);
////        if (signageBlock.isPresent()) {
////            signageBlockService.delete(signageBlock.get());
////            responseEntity.put("message", "刪除成功");
////            return new ResponseEntity<>(responseEntity, HttpStatus.OK);
////        } else {
////            responseEntity.put("message", "刪除失敗");
////            responseEntity.put("state", "Block 不存在");
////            return new ResponseEntity<>(responseEntity, HttpStatus.NOT_FOUND);
////        }
////    }
//}