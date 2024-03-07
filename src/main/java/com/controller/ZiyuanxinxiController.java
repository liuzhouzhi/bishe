package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.ZiyuanxinxiEntity;
import com.entity.view.ZiyuanxinxiView;

import com.service.ZiyuanxinxiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;
import com.service.StoreupService;
import com.entity.StoreupEntity;

/**
 * 资源信息
 * 后端接口
 * @date 2024-03-04 18:09:23
 */
@RestController
@RequestMapping("/ziyuanxinxi")
public class ZiyuanxinxiController {
    @Autowired
    private ZiyuanxinxiService ziyuanxinxiService;

    @Autowired
    private StoreupService storeupService;



    



    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ZiyuanxinxiEntity ziyuanxinxi,
		HttpServletRequest request){
        EntityWrapper<ZiyuanxinxiEntity> ew = new EntityWrapper<ZiyuanxinxiEntity>();

		PageUtils page = ziyuanxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, ziyuanxinxi), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ZiyuanxinxiEntity ziyuanxinxi, 
		HttpServletRequest request){
        EntityWrapper<ZiyuanxinxiEntity> ew = new EntityWrapper<ZiyuanxinxiEntity>();

		PageUtils page = ziyuanxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, ziyuanxinxi), params), params));
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ZiyuanxinxiEntity ziyuanxinxi){
       	EntityWrapper<ZiyuanxinxiEntity> ew = new EntityWrapper<ZiyuanxinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( ziyuanxinxi, "ziyuanxinxi")); 
        return R.ok().put("data", ziyuanxinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ZiyuanxinxiEntity ziyuanxinxi){
        EntityWrapper< ZiyuanxinxiEntity> ew = new EntityWrapper< ZiyuanxinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( ziyuanxinxi, "ziyuanxinxi")); 
		ZiyuanxinxiView ziyuanxinxiView =  ziyuanxinxiService.selectView(ew);
		return R.ok("查询资源信息成功").put("data", ziyuanxinxiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ZiyuanxinxiEntity ziyuanxinxi = ziyuanxinxiService.selectById(id);
		ziyuanxinxi.setClicktime(new Date());
		ziyuanxinxiService.updateById(ziyuanxinxi);
        ziyuanxinxi = ziyuanxinxiService.selectView(new EntityWrapper<ZiyuanxinxiEntity>().eq("id", id));
        return R.ok().put("data", ziyuanxinxi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ZiyuanxinxiEntity ziyuanxinxi = ziyuanxinxiService.selectById(id);
		ziyuanxinxi.setClicktime(new Date());
		ziyuanxinxiService.updateById(ziyuanxinxi);
        ziyuanxinxi = ziyuanxinxiService.selectView(new EntityWrapper<ZiyuanxinxiEntity>().eq("id", id));
        return R.ok().put("data", ziyuanxinxi);
    }
    


    /**
     * 赞或踩
     */
    @RequestMapping("/thumbsup/{id}")
    public R vote(@PathVariable("id") String id,String type){
        ZiyuanxinxiEntity ziyuanxinxi = ziyuanxinxiService.selectById(id);
        if(type.equals("1")) {
        	ziyuanxinxi.setThumbsupnum(ziyuanxinxi.getThumbsupnum()+1);
        } else {
        	ziyuanxinxi.setCrazilynum(ziyuanxinxi.getCrazilynum()+1);
        }
        ziyuanxinxiService.updateById(ziyuanxinxi);
        return R.ok("投票成功");
    }

    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ZiyuanxinxiEntity ziyuanxinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(ziyuanxinxi);
        ziyuanxinxiService.insert(ziyuanxinxi);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ZiyuanxinxiEntity ziyuanxinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(ziyuanxinxi);
        ziyuanxinxiService.insert(ziyuanxinxi);
        return R.ok();
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ZiyuanxinxiEntity ziyuanxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(ziyuanxinxi);
        ziyuanxinxiService.updateById(ziyuanxinxi);//全部更新
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<ZiyuanxinxiEntity> list = new ArrayList<ZiyuanxinxiEntity>();
        for(Long id : ids) {
            ZiyuanxinxiEntity ziyuanxinxi = ziyuanxinxiService.selectById(id);
            ziyuanxinxi.setSfsh(sfsh);
            ziyuanxinxi.setShhf(shhf);
            list.add(ziyuanxinxi);
        }
        ziyuanxinxiService.updateBatchById(list);
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        ziyuanxinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	
	/**
     * 前端智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,ZiyuanxinxiEntity ziyuanxinxi, HttpServletRequest request,String pre){
        EntityWrapper<ZiyuanxinxiEntity> ew = new EntityWrapper<ZiyuanxinxiEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "clicktime");
        params.put("order", "desc");
		PageUtils page = ziyuanxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, ziyuanxinxi), params), params));
        return R.ok().put("data", page);
    }


    /**
     * 协同算法（按收藏推荐）
     */
    @RequestMapping("/autoSort2")
    public R autoSort2(@RequestParam Map<String, Object> params,ZiyuanxinxiEntity ziyuanxinxi, HttpServletRequest request){
        String userId = request.getSession().getAttribute("userId").toString();
        String inteltypeColumn = "ziyuanfenlei";
        List<StoreupEntity> storeups = storeupService.selectList(new EntityWrapper<StoreupEntity>().eq("type", 1).eq("userid", userId).eq("tablename", "ziyuanxinxi").orderBy("addtime", false));
        List<String> inteltypes = new ArrayList<String>();
        Integer limit = params.get("limit")==null?10:Integer.parseInt(params.get("limit").toString());
        List<ZiyuanxinxiEntity> ziyuanxinxiList = new ArrayList<ZiyuanxinxiEntity>();
        //去重
        if(storeups!=null && storeups.size()>0) {
            for(StoreupEntity s : storeups) {
                ziyuanxinxiList.addAll(ziyuanxinxiService.selectList(new EntityWrapper<ZiyuanxinxiEntity>().eq(inteltypeColumn, s.getInteltype())));
            }
        }
        EntityWrapper<ZiyuanxinxiEntity> ew = new EntityWrapper<ZiyuanxinxiEntity>();
        params.put("sort", "id");
        params.put("order", "desc");
        PageUtils page = ziyuanxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, ziyuanxinxi), params), params));
        List<ZiyuanxinxiEntity> pageList = (List<ZiyuanxinxiEntity>)page.getList();
        if(ziyuanxinxiList.size()<limit) {
            int toAddNum = (limit-ziyuanxinxiList.size())<=pageList.size()?(limit-ziyuanxinxiList.size()):pageList.size();
            for(ZiyuanxinxiEntity o1 : pageList) {
                boolean addFlag = true;
                for(ZiyuanxinxiEntity o2 : ziyuanxinxiList) {
                    if(o1.getId().intValue()==o2.getId().intValue()) {
                        addFlag = false;
                        break;
                    }
                }
                if(addFlag) {
                    ziyuanxinxiList.add(o1);
                    if(--toAddNum==0) break;
                }
            }
        } else if(ziyuanxinxiList.size()>limit) {
            ziyuanxinxiList = ziyuanxinxiList.subList(0, limit);
        }
        page.setList(ziyuanxinxiList);
        return R.ok().put("data", page);
    }




    /**
     * （按值统计）
     */
    @RequestMapping("/value/{xColumnName}/{yColumnName}")
    public R value(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName,HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("yColumn", yColumnName);
        EntityWrapper<ZiyuanxinxiEntity> ew = new EntityWrapper<ZiyuanxinxiEntity>();
        List<Map<String, Object>> result = ziyuanxinxiService.selectValue(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }

    /**
     * （按值统计(多)）
     */
    @RequestMapping("/valueMul/{xColumnName}")
    public R valueMul(@PathVariable("xColumnName") String xColumnName,@RequestParam String yColumnNameMul, HttpServletRequest request) {
        String[] yColumnNames = yColumnNameMul.split(",");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        List<List<Map<String, Object>>> result2 = new ArrayList<List<Map<String,Object>>>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        EntityWrapper<ZiyuanxinxiEntity> ew = new EntityWrapper<ZiyuanxinxiEntity>();
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = ziyuanxinxiService.selectValue(params, ew);
            for(Map<String, Object> m : result) {
                for(String k : m.keySet()) {
                    if(m.get(k) instanceof Date) {
                        m.put(k, sdf.format((Date)m.get(k)));
                    }
                }
            }
            result2.add(result);
        }
        return R.ok().put("data", result2);
    }

    /**
     * （按值统计）时间统计类型
     */
    @RequestMapping("/value/{xColumnName}/{yColumnName}/{timeStatType}")
    public R valueDay(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName, @PathVariable("timeStatType") String timeStatType,HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("yColumn", yColumnName);
        params.put("timeStatType", timeStatType);
        EntityWrapper<ZiyuanxinxiEntity> ew = new EntityWrapper<ZiyuanxinxiEntity>();
        List<Map<String, Object>> result = ziyuanxinxiService.selectTimeStatValue(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }

    /**
     * （按值统计）时间统计类型(多)
     */
    @RequestMapping("/valueMul/{xColumnName}/{timeStatType}")
    public R valueMulDay(@PathVariable("xColumnName") String xColumnName, @PathVariable("timeStatType") String timeStatType,@RequestParam String yColumnNameMul,HttpServletRequest request) {
        String[] yColumnNames = yColumnNameMul.split(",");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("timeStatType", timeStatType);
        List<List<Map<String, Object>>> result2 = new ArrayList<List<Map<String,Object>>>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        EntityWrapper<ZiyuanxinxiEntity> ew = new EntityWrapper<ZiyuanxinxiEntity>();
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = ziyuanxinxiService.selectTimeStatValue(params, ew);
            for(Map<String, Object> m : result) {
                for(String k : m.keySet()) {
                    if(m.get(k) instanceof Date) {
                        m.put(k, sdf.format((Date)m.get(k)));
                    }
                }
            }
            result2.add(result);
        }
        return R.ok().put("data", result2);
    }

    /**
     * 分组统计
     */
    @RequestMapping("/group/{columnName}")
    public R group(@PathVariable("columnName") String columnName,HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("column", columnName);
        EntityWrapper<ZiyuanxinxiEntity> ew = new EntityWrapper<ZiyuanxinxiEntity>();
        List<Map<String, Object>> result = ziyuanxinxiService.selectGroup(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }




    /**
     * 总数量
     */
    @RequestMapping("/count")
    public R count(@RequestParam Map<String, Object> params,ZiyuanxinxiEntity ziyuanxinxi, HttpServletRequest request){
        EntityWrapper<ZiyuanxinxiEntity> ew = new EntityWrapper<ZiyuanxinxiEntity>();
        int count = ziyuanxinxiService.selectCount(MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, ziyuanxinxi), params), params));
        return R.ok().put("data", count);
    }



}
