package com.itplayer.client.cache;

import com.itplayer.core.system.entity.Permission;
import com.itplayer.core.system.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class Constant implements ApplicationListener<ContextRefreshedEvent> {
    public static Map<String, String> PERMISSION_MAP = new HashMap<String, String>();

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            try {
                initPermissionCache();
            } catch (Exception e) {

            }
        }
    }

    @Autowired
    PermissionService permissionService;

    private void initPermissionCache() {
        List<Permission> list = permissionService.findAll();
        if (list != null && list.size() > 0) {
            Map<String, String> map = new HashMap<String, String>();
            for (Permission permission : list) {
                map.put(permission.getPermissionCode(), permission.getPermissionName());
            }
            PERMISSION_MAP = map;
        }
    }
}
