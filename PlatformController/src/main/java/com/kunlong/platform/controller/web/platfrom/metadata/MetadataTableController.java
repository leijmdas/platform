package com.kunlong.platform.controller.web.platfrom.metadata;

import app.support.query.PageResult;
import com.alibaba.fastjson.JSON;
import com.kunlong.core.util.Base64;
import com.kunlong.dubbo.api.dto.FileInfoDTO;
import com.kunlong.dubbo.api.dto.queryParam.MetadataQueryDTO;
import com.kunlong.platform.config.datasource.PfTransactional;
import com.kunlong.platform.consts.ApiConstants;
import com.kunlong.platform.controller.web.BaseController;
import com.kunlong.platform.controller.web.annotation.DateRewritable;
import com.kunlong.platform.domain.CheckDictResult;
import com.kunlong.platform.domain.MetadataDictModel;
import com.kunlong.platform.domain.MetadataFieldModel;
import com.kunlong.platform.service.MetadataDictModelService;
import com.kunlong.platform.service.MetadataFieldModelService;
import com.kunlong.platform.service.MetadataJoinService;
import com.kunlong.platform.util.FileHelper;
import com.kunlong.platform.util.WebFileUtil;
import com.kunlong.platform.utils.JsonResult;
import com.kunlong.platform.utils.KunlongUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 *
 * 
 * @author ljming
 *
 */
@Api(value = "元数据工具", description = "元数据工具")

@RequestMapping(ApiConstants.PREFIX_SYS + "/metadata/table")
@Controller
public class MetadataTableController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MetadataJoinService metadataJoinService;
    @Autowired
    MetadataDictModelService dictModelService;
    @Autowired
    MetadataFieldModelService fieldModelService;

    @PostMapping("/selectTable")
    public @ResponseBody
    JsonResult<PageResult<Map<String, Object>>> selectTable(@RequestBody MetadataQueryDTO queryDTO) {
        if(!metadataJoinService.checkTableExists(queryDTO.getParam().getMetadataId()))
        {
            return JsonResult.failure(null,"表不存在！");
        }
        PageResult<Map<String, Object>> pageResult = metadataJoinService.selectTable(queryDTO);
        return JsonResult.success(pageResult);

    }

    @PostMapping("/checkDict")
    public @ResponseBody
    PageResult<CheckDictResult> checkDict(@RequestBody MetadataQueryDTO queryDTO) {
        return metadataJoinService.checkDict(queryDTO.getParam().getMetadataId());
    }

    @PostMapping("/copyMaster")
    public @ResponseBody
    JsonResult<Integer> copyMaster(@RequestBody MetadataQueryDTO queryDTO) {
        Integer id = metadataJoinService.copyMaster(queryDTO.getParam().getMetadataId());
        if (id < 0) {
            return JsonResult.failure(id, "元数据表不存在！");
        }
        return JsonResult.success(id);
    }

    @PostMapping("/dropDbTable")
    public @ResponseBody
    JsonResult<Integer> dropDbTable(@RequestBody MetadataQueryDTO queryDTO) {
        return metadataJoinService.dropDbTable(queryDTO.getParam().getMetadataId());

    }

    @PostMapping("/makeDbTable")
    public @ResponseBody
    JsonResult<Integer> makeDbTable(@RequestBody MetadataQueryDTO queryDTO) {
        return metadataJoinService.makeDbTable(queryDTO.getParam().getMetadataId());

    }

    @ApiOperation(value = "makeWebPage", notes = "生成页面代码", authorizations = {@Authorization(value = ApiConstants.AUTH_API_WEB)})
    @PostMapping("/makeWebPage")
    public @ResponseBody
    JsonResult<List<String>> makeFormCode(@RequestBody MetadataQueryDTO queryDTO) throws IOException {
        List<String> ret = metadataJoinService.makeWebPage(queryDTO.getParam().getMetadataId());
        return JsonResult.success(ret);
    }


    @PostMapping("/doSortMetadataDict")
    public @ResponseBody
    JsonResult<Integer> doSortMetadataDict(@RequestBody MetadataQueryDTO queryDTO) {
        logger.info("queryDTO: {}",KunlongUtils.toJSONStringPretty(queryDTO));
        metadataJoinService.doSortMetadataDict(queryDTO.getSubsysId(),queryDTO.getIds());
        return JsonResult.success();
    }

    @PostMapping("/doSortMetadataField")
    public @ResponseBody
    JsonResult<Integer> doSortMetadataField(@RequestBody MetadataQueryDTO queryDTO) {
        logger.info("queryDTO: {}",KunlongUtils.toJSONStringPretty(queryDTO));
        metadataJoinService.doSortMetadataField(queryDTO.getIds());

        return JsonResult.success();
    }

    @PostMapping("/dbImportTableFields/{metadataId}")
    public @ResponseBody
    JsonResult<Integer> dbImportTableFields(@PathVariable("metadataId") Integer metadataId) {
        int ret = metadataJoinService.dbImportTableFields(metadataId);
        if (ret < 0) {
            return JsonResult.failure(-1, "表在数据库中不存在！");
        }
        return JsonResult.success(ret);
    }

    @PostMapping("/dbImportTables/{subsysId}")
    public @ResponseBody
    JsonResult<List<Integer>> dbImportTables(@PathVariable("subsysId") Integer subsysId) {
        if (subsysId == null) {
            return JsonResult.failure(null, "subsysId is null!");
        }
        List<Integer> result = metadataJoinService.dbImportTables(subsysId);
        if (result.size() == 0) {
            return JsonResult.failure(result, "数据库中无表！");
        }
        return JsonResult.success(result);
    }
    //异步刷新
    @PostMapping("/dbImportTablesAsync/{subsysId}")
    public @ResponseBody
    JsonResult<List<Integer>> dbImportTablesAsync(@PathVariable("subsysId") Integer subsysId) {
        if (subsysId == null) {
            return JsonResult.failure(null, "subsysId is null!");
        }
        List<Integer> result = metadataJoinService.dbImportTablesAsync(subsysId);

        return JsonResult.success(result);
    }
    //增量刷新
    @PostMapping("/dbImportTablesInc/{subsysId}")
    public @ResponseBody
    JsonResult<List<Integer>> dbImportTablesInc(@PathVariable("subsysId") Integer subsysId) {
        if (subsysId == null) {
            return JsonResult.failure(null, "subsysId is null!");
        }
        List<Integer> result = metadataJoinService.dbImportTablesInc(subsysId);
        if (result.size() == 0) {
            return JsonResult.failure(result, "数据库无增量表需要刷新！");
        }
        return JsonResult.success(result);
    }

    // 处理文件上传
    @RequestMapping(value = "/uploadDict", method = RequestMethod.POST)
    public @ResponseBody
    JsonResult uploadDict(@RequestParam("file") MultipartFile uploadFile,
                       @RequestParam(value = "maxSize", defaultValue = "20480") Integer maxSize,
                          HttpServletRequest request)
            throws Exception {

        String filename = uploadFile.getOriginalFilename();
        filename = URLDecoder.decode(filename,"UTF-8");

        String contentType = uploadFile.getContentType();
        logger.info("接收文件[contentType:" + contentType + ",name:" + filename + "]");
        long fileSize = uploadFile.getSize();
        if (fileSize > maxSize*1024) {
            throw new RuntimeException("文件过大。最大允许(" + maxSize + " KB)");
        }
        String extName = FileHelper.getFileExtByFilename(filename);
        byte[] bytes = FileHelper.stream2byte(uploadFile.getInputStream());
        logger.info("uploadDict filename:{},\n content:\n{}", filename, new String(bytes, "UTF-8"));
        if(filename.contains("jsonarray")){
            metadataJoinService.importDictTables(bytes);

        }else {
            MetadataDictModel model = metadataJoinService.importDictTable(bytes);
            if (model == null) {
                metadataJoinService.importDictTables(bytes);
            }
        }

        return JsonResult.success();
    }

    @RequestMapping(value = "/exportDictTable/{metadataId}", method = RequestMethod.POST)
    @ApiOperation(value = "exportDictTable", notes = "exportDictTable", authorizations = {@Authorization(value = ApiConstants.AUTH_API_WEB)})
    public void exportDictTable(@PathVariable("metadataId") Integer metadataId, HttpServletRequest req, HttpServletResponse rsp) throws FileNotFoundException, IOException {

        MetadataDictModel dictModel  = metadataJoinService.exportDictTable( metadataId);

        WebFileUtil web = new WebFileUtil(req, rsp);
        //String baseStr =  Base64.encode(KunlongUtils.toJSONString(dictModel).getBytes());

        web.export2JsonFile(dictModel.getMetadataName() + ".json.txt", KunlongUtils.toJSONString(dictModel), rsp);

    }

    @RequestMapping(value = "/exportDictTables/{metadataIds}", method = RequestMethod.POST)
    @ApiOperation(value = "exportDictTables", notes = "exportDictTables", authorizations = {@Authorization(value = ApiConstants.AUTH_API_WEB)})
    public void exportDictTables(@PathVariable("metadataIds") String metadatIds, HttpServletRequest req, HttpServletResponse rsp) throws FileNotFoundException, IOException {

        List<MetadataDictModel> dictModels  = metadataJoinService.exportDictTables( metadatIds );
        WebFileUtil web = new WebFileUtil(req, rsp);
        //String baseStr =  Base64.encode(KunlongUtils.toJSONString(dictModels).getBytes());
        web.export2JsonFile( dictModels.get(0).getMetadataName()+"_models.jsonarray.txt", KunlongUtils.toJSONString(dictModels), rsp);

    }

}
