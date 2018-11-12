package shiro.demo

class BootStrap {

    def init = { servletContext ->

        if (Utilisateur.count() == 0){

            def adminRole = new Role(authority: 'ROLE_ADMIN')
            adminRole.save()
            def admin = new Utilisateur(username: 'admin', password: 'admin').save()
            UtilisateurRole.create(admin, adminRole, true)
            new Permission(user: admin, permission: "root").save()

            def userRole = new Role(authority: 'ROLE_USER').save()
            def user = new Utilisateur(username: 'user', password: 'user').save()
            UtilisateurRole.create(user, userRole, true)

        }
    }
    def destroy = {
    }
}
