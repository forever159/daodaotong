package com.hibi.www.service;

import com.hibi.www.domain.Menu;
import com.hibi.www.tools.Pages;


public interface MenuService {

    public Pages getMenuByPage(int page, int rows, String order, Menu key);
}
