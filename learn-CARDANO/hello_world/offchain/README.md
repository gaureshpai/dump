# [Next.js Application with HeroUI and Cardano Integration](https://github.com/gaureshpai/learn-CARDANO/tree/main/hello_world/offchain)

## Project Description

This project appears to be a Next.js application that integrates a comprehensive suite of HeroUI components for its user interface. The presence of `@evolution-sdk/lucid` suggests that it is also exploring or implementing functionalities related to the Cardano blockchain, likely for offchain interactions as indicated by its directory path. It might serve as a template or an experimental project for building dApps (decentralized applications) on Cardano with a rich UI.

## Tech Stack

This project utilizes a modern web development stack:

- **Framework:**
  - [Next.js](https://nextjs.org/) (v15) - React framework for server-side rendering, static site generation, and API routes.
  - [React](https://reactjs.org/) (v18) - A JavaScript library for building user interfaces.
- **UI Components:**
  - [HeroUI](https://heroui.com/) - A comprehensive collection of UI components for building modern web applications.
  - `framer-motion`: For animations.
- **Styling:**
  - [Tailwind CSS](https://tailwindcss.com/) - A utility-first CSS framework for rapid UI development.
  - `next-themes`: For theme management (e.g., dark mode).
- **Cardano Integration:**
  - `@evolution-sdk/lucid`: A library likely used for interacting with the Cardano blockchain.
- **Development Tools:**
  - TypeScript: For type-safe JavaScript.
  - ESLint, Prettier: For code linting and formatting.

## Scripts Overview

-   `dev`: Starts the Next.js development server.
-   `build`: Builds the Next.js application for production.
-   `start`: Starts the Next.js production server.
-   `lint`: Lints and automatically fixes code issues.

## Getting Started

To get a local copy up and running, follow these simple steps.

### Prerequisites

- Node.js (v18 or later)
- npm or yarn

### Installation

1.  Clone the repo
    ```sh
    git clone https://github.com/gaureshpai/learn-CARDANO.git
    cd learn-CARDANO/hello_world/offchain
    ```
2.  Install NPM packages
    ```sh
    npm install
    ```
3.  Set up `pnpm` (if used by the original template):
    If you are using `pnpm`, you might need to add the following to your `.npmrc` file and reinstall:
    ```
    public-hoist-pattern[]=*@heroui/*
    ```

### Running the Application

-   To run the development server:
    ```sh
    npm run dev
    ```