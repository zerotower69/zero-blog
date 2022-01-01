package xyz.zerotower.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.zerotower.blog.dto.ResourceDTO;
import xyz.zerotower.blog.dto.labelOptionDTO;
import xyz.zerotower.blog.entity.Resource;
import xyz.zerotower.blog.vo.ResourceVO;

import java.util.List;


/**
 * @author: zerotower
 * @date: 2021-04-01
 **/
public interface ResourceService extends IService<Resource> {

    /**
     * 导入swagger权限
     */
    void importSwagger();

    /**
     * 添加或修改资源
     * @param resourceVO
     */
    void saveOrUpdateResource(ResourceVO resourceVO);

    /**
     * 查看资源列表
     *
     * @return 资源列表
     */
    List<ResourceDTO> listResources();

    /**
     * 查看资源选项
     * @return 资源选项
     */
    List<labelOptionDTO> listResourceOption();

}
