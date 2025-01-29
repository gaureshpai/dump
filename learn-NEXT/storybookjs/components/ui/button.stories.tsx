import type { Meta, StoryObj } from "@storybook/react"

import { Button } from "./button"

import { action } from "@storybook/addon-actions"

const meta: Meta<typeof Button> = {
    title: "Components/ui/Button",
    component: Button,
    tags: ["autodocs"],
    parameters: {
        layout: "centered",
    },
    argTypes: {
        variant: {
            options: ["default", "destructive", "outline", "secondary", "ghost", "link"],
            description: "Variant of the button",
            control: { type: "select" },
        },
        size: {
            options: ["default", "sm", "lg", "icon"],
            description: "Size of the button",
            control: { type: "select" },
        },
        onClick: {
            action: "clicked",
            description: "Function to be called when the button is clicked",
        },
        children: {
            control: { type: "text" },
            description: "Content of the button",
        },
        className: {
            control: { type: "text" },
            description: "Additional tailwind css classes to be added to the button",
        },
        disabled: {
            control: { type: "boolean" },
            description: "Whether the button is disabled or not",
        },
        asChild: {
            control: { type: "boolean" },
            description: "Whether the button should render a child component or not",
        },
    },
}

export default meta;
type Story = StoryObj<typeof meta>;

export const Default: Story = {
    args: {
        variant: "default",
        size: "sm",
        disabled: false,
        onClick: action('default click'),
        children: "Default button",
        className: "shadow-sm",
    }
}

export const Primary: Story = {
    args: {
        variant: "default",
        size: "default",
        disabled: false,
        onClick: action('primary click'),
        children: "Primary button",
        className: "bg-blue-500 text-white hover:bg-blue-600",
    }
};

export const Secondary: Story = {
    args: {
        variant: "secondary",
        size: "default",
        disabled: false,
        onClick: action('secondary click'),
        children: "Secondary button",
        className: "bg-gray-500 text-white hover:bg-gray-600",
    }
};

export const Success: Story = {
    args: {
        variant: "ghost",
        size: "default",
        disabled: false,
        onClick: action('success click'),
        children: "Success button",
        className: "bg-green-500 text-white hover:bg-green-600",
    }
};

export const Danger: Story = {
    args: {
        variant: "link",
        size: "default",
        disabled: false,
        onClick: action('danger click'),
        children: "Danger button",
        className: "bg-red-500 text-white hover:bg-red-600",
    }
};

export const Outline: Story = {
    args: {
        variant: "outline",
        size: "default",
        disabled: false,
        onClick: action('outline click'),
        children: "Outline button",
        className: "border border-gray-500 text-gray-500 hover:bg-gray-100",
    }
};

export const Disabled: Story = {
    args: {
        variant: "destructive",
        size: "default",
        disabled: true,
        onClick: action('disabled click'),
        children: "Disabled button",
        className: "bg-gray-300 text-gray-500 cursor-not-allowed",
    }
};