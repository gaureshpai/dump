use aiken/collection/dict
use cardano/assets.{PolicyId}
use cardano/transaction.{OutputReference, Transaction}

validator hello_world {
  mint(redeemer token_name: ByteArray, policy_id: PolicyId, tx: Transaction) {
    expect [Pair(minted_token_name, qty)] =
      tx.mint |> assets.tokens(policy_id) |> dict.to_pairs

    when qty is {
      1 -> and {
          (minted_token_name == token_name)?,
          (token_name == "Change the hash")?,
        }

      -1 -> True

      _ -> fail @"Invalid Quantity"
    }
  }

  spend(
    _datum: Option<Data>,
    redeemer: Int,
    _o_ref: OutputReference,
    _tx: Transaction,
  ) {
    (redeemer == 42)?
  }

  else(_) {
    fail
  }
}
