package com.cskaoyan.service;

import com.cskaoyan.bean.backstage.user.User;
import com.cskaoyan.bean.wx.footprint.FootPrintList;
import com.cskaoyan.bean.wx.footprint.FootPrintVo;
import com.cskaoyan.mapper.UserMapper;
import com.cskaoyan.mapper.backstage.FootprintMapper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class FootPrintServiceImpl implements FootPrintService{

    @Autowired
    FootprintMapper footprintMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public void deleteFootPrint(Integer id)
    {
        footprintMapper.deleteByPrimaryKey(id);
    }

    @Override
    public FootPrintList queryFootprintList(Integer page,Integer size,String username)
    {
        User user = userMapper.selectUserByUsername(username);
        Integer userId = user.getId();
        List<FootPrintVo> footprints = footprintMapper.queryFootprintListByUserIdWithPage(userId,(page - 1) * size,size);
        int total = footprintMapper.queryFootprintTotalByUserId(userId);
        int totalPage = total % size == 0 ? total / size : total / size + 1;
        FootPrintList footPrintList = new FootPrintList();
        footPrintList.setFootprintList(footprints);
        footPrintList.setTotalPages(totalPage);
        return footPrintList;
    }
}
