package jpabasic.helloktl

fun main(args: Array<String>) {
    var canWrite: Int = 10;
    val onlyRead: Int = 10;

    canWrite += 1;
//    onlyRead += 1;
//    onlyRead = 20;

    println("canWrite = " + canWrite);
    println("onlyRead = " + onlyRead);

    // var: 값 변경 가능
    // val: 값 변경 불가
//*********************************************************************
    var notNull: Int = 10;
    var nullable: Int? = 10;

//    notNull = null;
    nullable = null;

    println("notNull = $notNull")
    println("nullable = $nullable")

    var str1: String = "ABC";
    var str2: String? = null;

    println("str1 = $str1")
    println("str2 = $str2")

//    str1 = str2;
    str2 = str1;

    println("str1 = $str1")
    println("str2 = $str2")

    // 타입에 ? 를 붙여 null 값을 허용할 수 있음
//*********************************************************************
    val s = "string"
    val i = 1
    val l = 1L
    val d = 1.0
    val f = 1.0f

    println("s = " + s::class)
    println("i = " + i::class)
    println("l = " + l::class)
    println("d = " + d::class)
    println("f = " + f::class)

    // 코틀린은 타입추론으로 변수의 값에 따라 타입을 알아서 지정해줌
}