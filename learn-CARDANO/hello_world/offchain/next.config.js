/** @type {import('next').NextConfig} */
const nextConfig = {
  async rewrites() {
    return [
      {
        source: "/koios/:path",
        destination: "https://preview.koios.rest/api/v1/:path"
      },
    ];
  },

  webpack: (config) => {
    config.experiments = {
      ...config.experiments,
      asyncWebAssembly: true,
      topLevelAwait: true,
      layers: true,
    };

    return config;
  },
};

module.exports = nextConfig;
