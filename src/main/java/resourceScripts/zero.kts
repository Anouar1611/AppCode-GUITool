try {
    println("Test :");
    var x = (5 / 0).toDouble()
} catch (e: Exception) {
      e.printStackTrace()
      /*val frame = e.stackTrace[1]
      println(frame.fileName)
      println(frame.lineNumber)
      println(Thread.currentThread().stackTrace[1].fileName)
      println(Thread.currentThread().stackTrace[1].lineNumber)*/
}