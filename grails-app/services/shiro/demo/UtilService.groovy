package shiro.demo

import grails.gorm.transactions.Transactional
import org.apache.shiro.authz.annotation.RequiresAuthentication
import org.apache.shiro.authz.annotation.RequiresPermissions

@Transactional
@RequiresAuthentication
class UtilService {

    def toUpper(String s) {
        s.toUpperCase()
    }

    @RequiresPermissions('root')
    def toLower(String s){
        s.toLowerCase()
    }
}
