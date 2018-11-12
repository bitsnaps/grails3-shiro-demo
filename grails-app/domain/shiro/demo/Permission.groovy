package shiro.demo

class Permission {

    Utilisateur user
    String permission

    static constraints = {
        permission(unique: 'user')
    }
}
