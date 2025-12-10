# [Storybook with Next.js Project](https://github.com/gaureshpai/storybookjs)

## Project Description

This project serves as a learning or experimental environment, integrating [Storybook](https://storybook.js.org/) with a [Next.js](https://nextjs.org/) application. It's designed for developing and showcasing UI components in isolation, making component development more efficient and testable.

## Tech Stack

This project utilizes a modern web development stack:

- **Framework:**
  - [Next.js](https://nextjs.org/) (v15) - React framework for server-side rendering, static site generation, and API routes.
  - [React](https://reactjs.org/) (v19) - A JavaScript library for building user interfaces.
- **Component Development:**
  - [Storybook](https://storybook.js.org/) (v8) - An open-source tool for developing UI components in isolation.
- **Styling:**
  - [Tailwind CSS](https://tailwindcss.com/) - A utility-first CSS framework for rapid UI development.
- **UI Components:**
  - [Radix UI](https://www.radix-ui.com/) - High-quality, accessible UI components.
  - `lucide-react`: For icons.

## Scripts Overview

-   `dev`: Starts the Next.js development server.
-   `build`: Builds the Next.js application for production.
-   `start`: Starts the Next.js production server.
-   `lint`: Lints the project code.
-   `storybook`: Starts the Storybook development server.
-   `build-storybook`: Builds the Storybook static application.

## Getting Started

To get a local copy up and running, follow these simple steps.

### Prerequisites

- Node.js (v18 or later)
- npm or yarn

### Installation

1.  Clone the repo
    ```sh
    git clone https://github.com/gaureshpai/storybookjs.git
    ```
2.  Install NPM packages
    ```sh
    npm install
    ```

### Running the Application

-   To run the Next.js development server:
    ```sh
    npm run dev
    ```
-   To run Storybook:
    ```sh
    npm run storybook
    ```