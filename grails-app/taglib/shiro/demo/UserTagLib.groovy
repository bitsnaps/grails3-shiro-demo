package shiro.demo

import shiro.demo.Utilisateur
import org.apache.shiro.SecurityUtils

class UserTagLib {

  static defaultEncodeAs = [taglib:'html']
  //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
  static namespace = "user"

  def loggedInUser = { attrs, body ->
      def user = _currentUser()
      if (!user) return
      def prop = user[attrs.property]
      if (prop) out << prop.encodeAsHTML()
  }

  def authenticated = { attrs, body ->
    if (SecurityUtils.subject.getPrincipal()){
      out << body()
    }
  }

  def _currentUser() {
      def subject = SecurityUtils.subject
      if (!subject.getPrincipal()) return // No-one logged-in
      return Utilisateur.findByUsername(subject.getPrincipal().toString())
  }

}
