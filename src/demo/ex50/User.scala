package demo.ex50

import java.util.{Calendar, Date}

case class User(name: String,
                email: String,
                age: Int,
                lovesBeer: Boolean,
                dateRegistered: Calendar)
