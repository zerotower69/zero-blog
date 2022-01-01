package xyz.zerotower.blog.controller;

import xyz.zerotower.blog.constant.StatusConst;
import xyz.zerotower.blog.dto.ResourceDTO;
import xyz.zerotower.blog.dto.labelOptionDTO;
import xyz.zerotower.blog.service.ResourceService;
import xyz.zerotower.blog.vo.ResourceVO;
import xyz.zerotower.blog.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author: yezhiqiu
 * @date: 2020-12-27
 **/
@Api(tags = "资源模块")
@RestController
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    @ApiOperation(value = "导入swagger接口")
    @GetMapping("/admin/resources/import/swagger")
    public Result importSwagger() {
        resourceService.importSwagger();
        return new Result<>(true, StatusConst.OK, "导入成功");
    }

    @ApiOperation(value = "查看资源列表")
    @GetMapping("/admin/resources")
    public Result<List<ResourceDTO>> listResources() {
        return new Result<>(true, StatusConst.OK, "查询成功", resourceService.listResources());
    }

    @ApiOperation(value = "删除资源")
    @DeleteMapping("/admin/resources")
    public Result deleteResources(@RequestBody List<Integer> resourceIdList) {
        resourceService.removeByIds(resourceIdList);
        return new Result<>(true, StatusConst.OK, "删除成功");
    }

    @ApiOperation(value = "新增或修改资源")
    @PostMapping("/admin/resources")
    public Result saveOrUpdateResource(@RequestBody @Valid ResourceVO resourceVO) {
        resourceService.saveOrUpdateResource(resourceVO);
        return new Result<>(true, StatusConst.OK, "操作成功");
    }

    @ApiOperation(value = "查看角色资源选项")
    @GetMapping("/admin/role/resources")
    public Result<List<labelOptionDTO>> listResourceOption() {
        return new Result<>(true, StatusConst.OK, "查询成功", resourceService.listResourceOption());
    }


}
