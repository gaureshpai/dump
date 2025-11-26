# hello_world

Write validators in the `validators` folder, and supporting functions in the `lib` folder using `.ak` as a file extension.

```aiken
validator my_first_validator {
  spend(_datum: Option<Data>, _redeemer: Data, _output_reference: Data, _context: Data) {
    True
  }
}
```

## Building

```sh
aiken b -t verbose
```

It will generate a [`plutus.json`](plutus.json) file.

You can copy the `compiledCode` into [`offchain/config/script.ts`](./offchain/config/script.ts) and replace the script with your compiled code.

To run the off-chain, go to [`offchain/`](./offchain/) and run `pnpm dev`

```sh
cd offchain
pnpm dev
```
