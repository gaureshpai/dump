export async function getErrorMessage(error: any) {
  const { info, message } = error;
  const errorMessage = `${message}`;

  try {
    // KoiosError:
    const a = errorMessage.indexOf("{", 1);
    const b = errorMessage.lastIndexOf("}", errorMessage.lastIndexOf("}") - 1) + 1;

    const rpc = errorMessage.slice(a, b);
    const jsonrpc = JSON.parse(rpc);

    const errorData = jsonrpc.error.data[0].error.data;

    try {
      const { validationError, traces } = errorData;

      console.error({ [validationError]: traces });

      return `${validationError} Traces: ${traces.join(", ")}.`;
    } catch {
      const { reason } = errorData;

      console.error(reason);

      return `${reason}`;
    }
  } catch {
    function toJSON(error: any) {
      try {
        const errorString = JSON.stringify(error);
        const errorJSON = JSON.parse(errorString);

        return errorJSON;
      } catch {
        return {};
      }
    }

    const { cause } = toJSON(error);
    const { failure } = cause ?? {};

    const failureCause = failure?.cause;

    let failureTrace: string | undefined;

    try {
      failureTrace = eval(failureCause).replaceAll(" Trace ", " \n ");
    } catch {
      failureTrace = undefined;
    }

    const failureInfo = failureCause?.info;
    const failureMessage = failureCause?.message;

    console.error(failureCause ?? { error });

    return `${failureTrace ?? failureInfo ?? failureMessage ?? info ?? message ?? error}`;
  }
}
