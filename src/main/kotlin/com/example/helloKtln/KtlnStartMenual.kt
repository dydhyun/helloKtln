package com.example.helloKtln

fun main(args: Array<String>) {
    println("Kotlin codeStyle setting")

    println("Setting -> Editor -> CodeStyle -> Kotlin -> SetForm -> kotlinStyleGuide")
    // 코틀린 스타일 가이드를 따르는지 확인하는 설정하기
    println("Setting -> Editor -> Inspections -> General -> Incorrect formating")
    println("========================================================================")

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
//*********************************************************************
    val num: Int = 20

    if(num in arrayOf(10,20,30,40)) {
        println("contain")
    } else{
        println("not contain")
    }
    // 배열에서 값과 일치하는 결과를 찾으면 true 를 리턴하는 in

    when (num) {
        10 -> println("num is 10")
        in 20..29 -> println("$num between 20~29")
        else -> println("no search in range")
    }
    // switch 와 비슷한 역할을 하는 when
//*********************************************************************
    val price1: Int = 1000
    val price2: Int = 2000

    val price3 = sumPrice(price1, price2)

    println(price3)

    val id = "원"
    println(sumPriceId(price3, id))

    /* 코틀린의 함수는 fun 으로 시작하며,
    fun 함수명(매개변수: 타입): 리턴타입 {
        함수의 본문
        return 반환값
    }
    와 같은 구조. */
//*********************************************************************
    val item = Item("Book", 10_000)

    println("name: ${item.name}, price: ${item.price}")

    item.buy()
    item.sell()
}

//fun sumPrice(price1: Int, price2: Int): Int = price1 + price2
// <- return 생략 / 반환타입 생략 ->
fun sumPrice(price1: Int, price2: Int) = price1 + price2

fun sumPriceId(price: Int, id: String): String {
    return "$price$id"
}

class Item(
    val name: String,
    val price: Int
) : ItemTrade {
    override fun buy() {
        println("buy $name")
    }

    override fun sell() {
//        TODO("NOT YET IMPLEMENT") 호출 시 다음 에러 발생
//        Exception in thread "main" kotlin.NotImplementedError: An operation is not implemented: NOT YET IMPLEMENT
        println("sell $name")
    }
}

interface ItemTrade {
    fun buy()
    fun sell()
}