package demo.ex50

import java.util.Calendar

class DevClubValidator {

  private def isValidName(name: String) =
    name.size >= 2

  private def isValidAge(age: Int) =
    age >= 18

  private def isValidEmail(email: String) = {
    val emailRegexp = "^[\\w\\-]+(\\.[\\w\\-]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$"
    email.matches(emailRegexp)
  }

  private def isValidRegDate(regDate: Calendar) = {
    val calToday = Calendar.getInstance()
    regDate.before(calToday)
  }

  /**
   *
   */
  def isValidUser(user: User) = {

    if (isValidName(user.name) &&
      isValidAge(user.age) &&
      isValidEmail(user.email) &&
      user.lovesBeer &&
      isValidRegDate(user.dateRegistered))
      true
    else
      false
  }

  /**
   *
   */
  def isValidUserThrowsExceptions(user: User) = {

    if (!isValidName(user.name))
      throw new IllegalArgumentException("Too short name.")

    if (!isValidAge(user.age))
      throw new IllegalArgumentException("Sorry, try later.")

    if (!isValidEmail(user.email))
      throw new IllegalArgumentException("Invalid e-mail")

    if (!user.lovesBeer)
      throw new IllegalArgumentException("Did you think twice before ?")

    if (!isValidRegDate(user.dateRegistered))
      throw new IllegalArgumentException("Invalid registration date")

    true //valid
  }

}