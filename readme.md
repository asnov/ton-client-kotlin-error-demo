1) It works fine through abiTest() with client.abi.encodeMessage(params) call.
So I can see both `>> Encode message request...` and `>> Encode message response:` in console.
3) It stucks inside tonClientTest() on client.buildInfo() call.
So I can see `>> Build info request...` but **not** `>> Build info response:` in console.
3) It stucks inside accountTest() on client.net.accounts.query() call.
So I can see `>> Account query request...` but **not** `>> Account query response:` in console.

