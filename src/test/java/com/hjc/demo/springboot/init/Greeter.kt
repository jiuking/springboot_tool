package com.hjc.demo.springboot.init

class Greeter(val name: String){
    fun greet() {
        println("hello,$name")
    }
}
fun main(args: Array<String>) {
    println("hello world")
//    Greeter(args[0]).greet()
    println(sum(1,3))
    println(sum1(1,4))
    printSum(3,4)
}
fun sum(a: Int, b: Int): Int{
    var c= 12
    c = 3
    println(c)
    return a+b
}
fun sum1(a:Int,b:Int) = a +b

fun printSum(a: Int, b: Int): Unit = println(a+b)
