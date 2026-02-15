declare module "*.css" {
  interface CSSModule {
    [className: string]: string;
  }
  const cssModule: CSSModule;
  export default cssModule;
}
