import ee.nx01.tonclient.TonClient

suspend fun main() {
    val client = TonClient()

    print("version: ")
    println(client.version())

    print("build info: ")
    println(client.buildInfo())

    client.destroy()
    println("El fin.")
}