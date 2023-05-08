import ee.nx01.tonclient.NetworkConfig
import ee.nx01.tonclient.TonClient
import ee.nx01.tonclient.TonClientConfig
import ee.nx01.tonclient.TonUtils
import ee.nx01.tonclient.abi.CallSet
import ee.nx01.tonclient.abi.KeyPair
import ee.nx01.tonclient.abi.ParamsOfEncodeMessage
import ee.nx01.tonclient.abi.Signer
import ee.nx01.tonclient.net.AccountType
import ee.nx01.tonclient.types.AccountFilterInput
import ee.nx01.tonclient.types.StringFilterInput
import java.math.BigDecimal

object TestConstants {
    val WALLET_ADDRESS = "0:7f458ae01e28573a181e2227dc77710d6421d4e103fdd3e023200aa4bce83950"
    val CONFIG = TonClientConfig(NetworkConfig(endpoints = listOf("https://devnet.evercloud.dev/00ce8109d59745d9b0ee5207f1ebb46b/graphql")))
    val CONFIG_MAIN = TonClientConfig(NetworkConfig(endpoints = listOf("https://mainnet.evercloud.dev/00ce8109d59745d9b0ee5207f1ebb46b/graphql")))
}

suspend fun main() {
    val client = TonClient()

    abiTest(client) // This works

//    tonClientTest(client)   // This doesn't work
    accountTest()           // This doesn't work as well

    client.destroy()
    println(">> El fin.")
}

suspend fun tonClientTest(client: TonClient) {
    println(">> Version request...")
    val version = client.version()
    println(">> Version response: $version")

    println(">> Build info request...")
    val info = client.buildInfo()
    println(">> Build info response: $info")
}

suspend fun abiTest(client: TonClient) {
    val params = ParamsOfEncodeMessage(
        abi = TonUtils.readAbi("setcodemultisig/SetcodeMultisigWallet.abi.json"),
        address = "0:1072926c848133157d63e8c1691bce79bbbd459347be47dab85536903894aeb3",
        callSet = CallSet(
            "submitTransaction",
            input = mapOf(
                "dest" to "0:ee946898dee44b9b7d4ed452fae4dba773ec339974b2e75223e868214ac01dfe",
                "value" to TonUtils.convertToken(BigDecimal(0.1)),
                "bounce" to false,
                "allBalance" to false,
                "payload" to ""
            ),
            header = null
        ),
        signer = Signer(
            keys = KeyPair(
                "7ef364d02bdf489a56714553dd66260666d52d4b03c5abd6ce62ec7ffbc0a2ca",
                "db5da80d3bdeb607d17cf29d1c68489b5071637b3a0d8d747b7ad6ce7e89e5c0"
            )
        )
    )

    println(">> Encode message request...")
    val response = client.abi.encodeMessage(params)
    println(">> Encode message response: $response")
    println(">> Encode message response address: ${response.address}")
}

suspend fun accountTest() {
    val client = TonClient(TestConstants.CONFIG)

    println(">> Accounts request...")
    val accountList = client.net.accounts.query(
        AccountFilterInput(id = StringFilterInput(eq = TestConstants.WALLET_ADDRESS)),
        "id acc_type boc last_paid balance"
    )
    println(">> Accounts response: $accountList")
    println(">> Accounts list size: ${accountList.size}")
    println(">> Accounts list first element: ${accountList[0]}")
    println(">> Accounts list first element type: ${accountList[0].accType}")
    println(">> Accounts list first element balance: ${accountList[0].getBalance()}")
}