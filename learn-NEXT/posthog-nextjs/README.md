# [PostHog Integration with Next.js](https://github.com/gaureshpai/posthog-nextjs)

## Project Description

This project demonstrates the integration of [PostHog](https://posthog.com/) analytics into a [Next.js](https://nextjs.org/) application. It serves as an example or a foundational setup for tracking user behavior and events within a Next.js environment using the `posthog-js` library.

## Tech Stack

This project utilizes a modern web development stack:

- **Framework:**
  - [Next.js](https://nextjs.org/) (v16) - React framework for server-side rendering, static site generation, and API routes.
  - [React](https://reactjs.org/) (v19) - A JavaScript library for building user interfaces.
- **Analytics:**
  - [PostHog](https://posthog.com/) (`posthog-js`) - Open-source product analytics.
- **Styling:**
  - [Tailwind CSS](https://tailwindcss.com/) - A utility-first CSS framework for rapid UI development.
- **Development Tools:**
  - [Biome.js](https://biomejs.dev/) - Linter and formatter.
  - TypeScript: For type-safe JavaScript.

## Scripts Overview

-   `dev`: Starts the Next.js development server.
-   `build`: Builds the Next.js application for production.
-   `start`: Starts the Next.js production server.
-   `lint`: Lints the project code using ESLint.

## Getting Started

To get a local copy up and running, follow these simple steps.

### Prerequisites

- Node.js (v18 or later)
- npm or yarn

### Installation

1.  Clone the repo
    ```sh
    git clone https://github.com/gaureshpai/posthog-nextjs.git
    ```
2.  Install NPM packages
    ```sh
    npm install
    ```
3.  Set up your environment variables. Copy the `.env.example` file (if present) to a new file named `.env` and fill in any required PostHog API keys or host URLs.

### Running the Application

-   To run the development server:
    ```sh
    npm run dev
    ```
