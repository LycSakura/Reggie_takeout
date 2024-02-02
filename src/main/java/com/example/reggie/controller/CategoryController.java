package com.example.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.reggie.common.R;
import com.example.reggie.entity.Category;
import com.example.reggie.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 *@title CategoryController
 *@CreateTime 2024/1/29 14:51
 *@description
 **/
@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * @param category
     * @return R<String>
     * @description: 新增分类
     */
    @PostMapping
    public R<String> save(@RequestBody Category category) {
        log.info("category" + category);
        categoryService.save(category);
        return R.success("新增分类成功");
    }

    /**
     * @param page
     * @param pageSize
     * @return R<Page>
     * @description: 分页查询
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize) {
        Page<Category> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<Category> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.orderByAsc(Category::getSort);
        categoryService.page(pageInfo, lambdaQueryWrapper);
        return R.success(pageInfo);
    }

    /**
     * @param ids
     * @return R<String>
     * @description: 根据id删除分类
     */
    @DeleteMapping
    public R<String> delete(Long ids) {
        log.info("删除分类，id为:" + ids);
        categoryService.remove(ids);
        return R.success("分类信息删除成功");
    }

    /**
     * @param category
     * @return R<String>
     * @description: 根据id修改分类信息
     */
    @PutMapping
    public R<String> update(@RequestBody Category category) {
        log.info("修改分类信息" + category);
        categoryService.updateById(category);
        return R.success("修改分类信息成功");
    }

    /**
     * @param category
     * @return R<List < Category>>
     * @description: 根据条件查询分类数据
     */
    @GetMapping("/list")
    public R<List<Category>> list(Category category) {
        LambdaQueryWrapper<Category> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        lambdaQueryWrapper.eq(category.getType() != null, Category::getType, category.getType());
        lambdaQueryWrapper.orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);

        List<Category> list = categoryService.list(lambdaQueryWrapper);
        return R.success(list);
    }
}
