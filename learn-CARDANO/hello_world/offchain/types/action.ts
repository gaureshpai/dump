export type Action = (params: any) => Promise<void>;
export type ActionGroup = Record<string, Action>;
