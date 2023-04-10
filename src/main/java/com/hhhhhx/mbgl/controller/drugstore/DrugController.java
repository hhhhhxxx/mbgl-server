package com.hhhhhx.mbgl.controller.drugstore;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hhhhhx.mbgl.dto.ClassificationDTO;
import com.hhhhhx.mbgl.dto.DrugInfoDTO;
import com.hhhhhx.mbgl.dto.DrugShopItem;
import com.hhhhhx.mbgl.dto.DrugViewDto;
import com.hhhhhx.mbgl.entity.Drug;
import com.hhhhhx.mbgl.entity.DrugType;
import com.hhhhhx.mbgl.entity.Stock;
import com.hhhhhx.mbgl.entity.result.RestResponse;
import com.hhhhhx.mbgl.param.BasePage;
import com.hhhhhx.mbgl.param.drugstore.drug.DrugSearchParam;
import com.hhhhhx.mbgl.param.drugstore.drug.DrugTypeSearchParam;
import com.hhhhhx.mbgl.service.drugstore.IDrugService;
import com.hhhhhx.mbgl.service.drugstore.IDrugTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 药品表 前端控制器
 * </p>
 *
 * @author hhhhhx
 * @since 2023-02-25
 */
@RestController
@RequestMapping("/drug")
public class DrugController {
    @Autowired
    private IDrugService drugService;

    @Autowired
    private IDrugTypeService drugTypeService;

    @GetMapping("/page/view")
    public RestResponse<IPage<DrugViewDto>> pageList(DrugSearchParam param) {

        return RestResponse.ok(drugService.pageDrugByParam(param));
    }

    @GetMapping("/get/info/{id}")
    public RestResponse<DrugInfoDTO> getInfo(@PathVariable @NotNull Integer id) {

        return RestResponse.ok(drugService.getDrugInfoById(id));
    }

    @GetMapping("/get/pre/shoplist/{preid}")
    public RestResponse<List<DrugShopItem>> getPreShopList(@PathVariable("preid") @NotNull Integer preId) {
        return RestResponse.ok(drugService.getPreShopList(preId));
    }


    @GetMapping("/page/list")
    public RestResponse<IPage<Drug>> pageList2(@Valid DrugSearchParam param) {
        return RestResponse.ok(drugService.pageList(param));
    }

    @GetMapping("/get/{id}")
    public RestResponse<Drug> getOne(@NotNull @PathVariable("id") Integer id) {
        return RestResponse.ok(drugService.getById(id));
    }

    @PostMapping("/su")
    public RestResponse<Boolean> pageList(@RequestBody Drug drug) {
        return RestResponse.ok(drugService.saveOrUpdate(drug));
    }
    @PostMapping("/delete/{id}")
    public RestResponse<Boolean> delete(@NotNull @PathVariable("id") Integer id) {
        return RestResponse.ok(drugService.removeById(id));
    }

    @GetMapping("/list")
    public RestResponse<List<Drug>> list() {
        return RestResponse.ok(drugService.list());
    }


    // -------------------------------------药品种类drugType
    @GetMapping("/get/class")
    public RestResponse<List<ClassificationDTO>> getClazz() {
        return RestResponse.ok(drugService.getClazz());
    }


    @GetMapping("/type/get/{id}")
    public RestResponse<DrugType> getDrugTypeById(@PathVariable @NotNull Integer id) {
        return RestResponse.ok(drugTypeService.getById(id));
    }

    @GetMapping("/type/list")
    public RestResponse<List<DrugType>> typeList() {
        return RestResponse.ok(drugTypeService.list());
    }

    @GetMapping("/type/page/list")
    public RestResponse<IPage<DrugType>> typePageList(DrugTypeSearchParam param) {
        return RestResponse.ok(drugTypeService.pageList(param));
    }
    @PostMapping("/type/su")
    public RestResponse<Boolean> typeSU(@RequestBody DrugType drugType) {
        return RestResponse.ok(drugTypeService.saveOrUpdate(drugType));
    }
    @GetMapping("/delete/{id}")
    public RestResponse<Boolean> typeDelete(@NotNull @PathVariable("id") Integer id) {
        return RestResponse.ok(drugService.removeById(id));
    }
}
