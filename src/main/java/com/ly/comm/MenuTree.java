package com.ly.comm;

import com.ly.Global;
import com.ly.sys.service.MenuService;
import com.ly.sys.vo.Menu;
import org.nutz.dao.Cnd;

import java.util.List;

public class MenuTree {
    public static String getMenuTree()
    {
        StringBuffer sb = new StringBuffer();
        MenuService menuService =  AppContext.getBean(MenuService.class);

        List<Menu> pmenu = menuService.query(Cnd.wrap("pname = '0' and state = 1 "),null);
        List<Menu> subMenu = null;

        for (Menu pm : pmenu)
        {
            sb.append("<div class=\"accordionHeader\"> \n");
            sb.append("<h2><span>Folder</span>"+pm.getCnname()+"</h2> \n");
            sb.append("</div>\n");

            sb.append("<div class=\"accordionContent\"> \n");
            sb.append("<ul class=\"tree treeFolder\"> \n");


            subMenu = menuService.query(Cnd.where("pname","=",pm.getName()).and("state","=","1"),null);//  Menu.menuDao.getListMenu(pm.getStr("name"));

            for (Menu sm : subMenu)
            {
                sb.append("<li><a href=\"/"+ Global.PROJECT_NAME+sm.getUrl()+"\" target=\"navTab\" rel=\""+sm.getName()+"\">"+sm.getCnname()+"</a></li>\n");
            }
            sb.append("</ul>\n</div>\n");
        }

        return sb.toString();

    }
}
