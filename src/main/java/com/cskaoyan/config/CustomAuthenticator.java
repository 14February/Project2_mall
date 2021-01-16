package com.cskaoyan.config;

import com.cskaoyan.token.MallToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomAuthenticator extends ModularRealmAuthenticator{
    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException
    {
        assertRealmsConfigured();
        Collection<Realm> originRealms = getRealms();
        MallToken token = (MallToken)authenticationToken;
        String type = token.getType();
        List<Realm> realms = new ArrayList<>();
        for(Realm originRealm : originRealms)
        {
            if(originRealm.getName().toLowerCase().contains(type)) realms.add(originRealm);
        }
        if (realms.size() == 1) {
            return doSingleRealmAuthentication(realms.iterator().next(), authenticationToken);
        } else {
            return doMultiRealmAuthentication(realms, authenticationToken);
        }
    }
}
