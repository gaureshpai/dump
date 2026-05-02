import fs from "fs";

const entriesPath = "diary_entries.json";
const today = new Date("2026-05-01");
const startDate = new Date("2026-01-29");

const missingWeekendsData = [
  {
    date: "2026-02-08",
    description:
      "Started architectural planning for the modern Todo monorepo, focusing on the integration of Protocol Buffers (Connect RPC) for type-safe communication between React and Node.js.",
    learnings:
      "Learned how Protobuf can enforce strict contracts between frontend and backend, reducing runtime errors and improving developer productivity in a monorepo setup.",
  },
  {
    date: "2026-02-15",
    description:
      "Configured the monorepo tooling using pnpm Workspaces and Turborepo. Set up the basic structure for 'backend', 'frontend', and 'proto' directories.",
    learnings:
      "Gained insights into Turborepo's caching mechanisms and how pnpm workspaces manage shared dependencies efficiently across different packages.",
  },
  {
    date: "2026-02-22",
    description:
      "Defined the initial .proto schemas for the Todo service, including Create, Read, Update, and Delete operations for tasks.",
    learnings:
      "Understood the syntax of Protobuf messages and services, and how Connect-RPC generates TypeScript interfaces from these definitions.",
  },
  {
    date: "2026-03-01",
    description:
      "Implemented the Express.js backend shell and integrated the generated Connect-RPC handlers. Connected the backend to a MongoDB instance using Mongoose.",
    learnings:
      "Learned how to map Protobuf service definitions to actual Express route handlers and how Mongoose schemas can align with Protobuf types.",
  },
  {
    date: "2026-03-08",
    description:
      "Bootstrapped the React 19 frontend using Vite and SWC. Integrated TanStack Router for file-based routing and planned the layout.",
    learnings:
      "Explored the new features of React 19 and the benefits of file-based routing in TanStack Router for better code organization and type safety.",
  },
  {
    date: "2026-03-15",
    description:
      "Developed the core Todo list UI components using Tailwind CSS and shadcn/ui. Focused on creating a clean, responsive design.",
    learnings:
      "Deepened knowledge of Tailwind CSS utility classes and how shadcn/ui provides accessible, unstyled components that are easy to customize.",
  },
  {
    date: "2026-03-22",
    description:
      "Integrated TanStack Query on the frontend to handle data fetching from the Connect-RPC backend. Implemented optimistic UI updates for task completion.",
    learnings:
      "Learned how TanStack Query's mutation hooks can be used with 'onMutate' to provide an instantaneous user experience while background syncing.",
  },
  {
    date: "2026-03-28",
    description:
      "Worked on implementing authentication logic using JWT and securing the Connect-RPC endpoints in the Express backend.",
    learnings:
      "Understood the flow of stateless authentication and how to use middleware in Express to validate tokens for RPC calls.",
  },
  {
    date: "2026-03-29",
    description:
      "Refined the frontend authentication flow, adding login and signup pages with form validation powered by Biome's linting rules.",
    learnings:
      "Observed how Biome can replace multiple tools like ESLint and Prettier, offering faster performance and simplified configuration.",
  },
  {
    date: "2026-04-04",
    description:
      "Enhanced the Todo application with task categorization and filtering based on priority and due dates.",
    learnings:
      "Learned complex MongoDB aggregation queries for filtering and how to reflect these filters in the TanStack Router search parameters.",
  },
  {
    date: "2026-04-05",
    description:
      "Implemented real-time notifications for task deadlines using a combination of backend cron jobs and frontend alerts.",
    learnings:
      "Explored strategies for managing scheduled tasks in Node.js and providing timely feedback to users without constant polling.",
  },
  {
    date: "2026-04-11",
    description:
      "Added support for dark mode using Tailwind CSS and integrated a theme switcher component from shadcn/ui.",
    learnings:
      "Mastered the use of 'dark:' variants in Tailwind and how to persist user theme preferences in local storage.",
  },
  {
    date: "2026-04-12",
    description:
      "Spent the day optimizing the application's performance, focusing on minimizing bundle size and improving First Contentful Paint.",
    learnings:
      "Learned about Vite's build optimizations and how to analyze bundle sizes to identify and remove redundant dependencies.",
  },
  {
    date: "2026-04-19",
    description:
      "Set up automated testing for the backend services using Vitest. Wrote unit tests for the Connect-RPC logic.",
    learnings:
      "Gained experience in writing testable RPC handlers and mocking database connections for reliable unit testing.",
  },
  {
    date: "2026-04-25",
    description:
      "Implemented end-to-end testing for critical user flows using Playwright. Verified the Todo creation and deletion cycle.",
    learnings:
      "Understood the importance of E2E tests in catching regressions that unit tests might miss, especially in a full-stack environment.",
  },
  {
    date: "2026-04-26",
    description:
      "Finalized the deployment pipeline using GitHub Actions to deploy the frontend to Vercel and the backend to a cloud provider.",
    learnings:
      "Learned how to configure CI/CD pipelines for monorepos, ensuring that only the changed packages are built and deployed.",
  },
];

let entries = [];
try {
  entries = JSON.parse(fs.readFileSync(entriesPath, "utf-8"));
} catch (e) {
  console.error("Error reading entries:", e);
  process.exit(1);
}

const existingDates = new Set(entries.map((e) => e.date));

missingWeekendsData.forEach((item) => {
  if (!existingDates.has(item.date)) {
    entries.push({
      date: item.date,
      description: item.description,
      hours: 8,
      learnings: item.learnings,
      blockers: "None",
      mood_slider: 5,
      status: 2,
      internship: { name: "Frontend Developer" },
    });
    console.log(`Prepared entry for ${item.date}`);
  }
});

entries.sort((a, b) => new Date(b.date) - new Date(a.date));

fs.writeFileSync(entriesPath, JSON.stringify(entries, null, 2));
console.log("Updated diary_entries.json with unique weekend entries.");
