# 🔌 Embabel Framework: Confluence MCP Server

This repository demonstrates how to build a **Confluence MCP Server** using the **Embabel Framework** and Spring Boot. The application acts as a bridge between AI agents (like Claude Desktop) and the **Atlassian Confluence REST API**, allowing the AI to manage documentation, list spaces, and create pages through natural language.

> **⚠️ Note:** This is **not** an official Confluence MCP server provided by Atlassian. It is a **demo** Confluence MCP server created specifically to demonstrate how developers can build their own MCP servers that interact with REST APIs using the Embabel Framework.

📖 **Complete Guide**: For detailed explanations and a full code walkthrough, read our comprehensive tutorial.<br>
👉 [**Embabel Framework: Build Confluence MCP Server**](https://bootcamptoprod.com/embabel-confluence-mcp-server/)

🎥 **Video Tutorial**: Prefer hands-on learning? Watch our step-by-step implementation guide.<br>
👉 YouTube Tutorial - [**Connect AI to Confluence: Build an MCP Server Using the Embabel Framework**](https://youtu.be/XEcjdwEY9Q0)

<p align="center">
  <a href="https://youtu.be/XEcjdwEY9Q0">
    <img src="https://img.youtube.com/vi/XEcjdwEY9Q0/0.jpg" alt="Connect AI to Confluence: Build an MCP Server Using the Embabel Framework" />
  </a>
</p>

<p align="center">
  ▶️ <a href="https://youtu.be/XEcjdwEY9Q0">Watch on YouTube</a>
</p>

---

## ✨ What This Project Demonstrates

This application showcases how to **connect AI agents to external REST APIs** using the MCP Server:

- **Model Context Protocol (MCP)** implementation using Embabel's `embabel-agent-starter-mcpserver`.
- **REST API Integration** connecting an AI agent to Confluence Cloud behind the scenes.
- **Tool Exposure** using Embabel's `@Export` annotation to turn Java service methods into AI tools.
- **Confluence Operations** including listing spaces, creating pages, fetching history, and reading metadata.

---

## 📋 Prerequisites

Before running this application, ensure you have:

- **Java 21** or higher
- **OpenRouter API Key** (free tier available at [OpenRouter.ai](https://openrouter.ai/))
- **Node.js** (Optional, required if testing with MCP Inspector)
- **Claude Desktop App** (Optional, for real-world agent testing)

---

## 🚀 Quick Start

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/BootcampToProd/embabel-confluence-mcp-server.git
cd eembabel-confluence-mcp-server
```

### 2️⃣ Configure API Keys
Provide your OpenRouter API key, Confluence base url and confluence token as environment variables.
```bash
OPENAI_API_KEY={YOUR_OPENROUTER_API_KEY}
CONFLUENCE_BASE_URL="https://your-domain.atlassian.net/wiki/api/v2"
CONFLUENCE_AUTH_TOKEN="confluence-base64-encoded-token"
```

### 3️⃣ Build the Project
```bash
mvn clean install
```

### 4️⃣ Run the Application
```bash
mvn spring-boot:run
```
The server will start on `http://localhost:8080`. The MCP endpoint is exposed at `/sse`.

---

## 💡 How to Test

You can test the server using **Claude Desktop** or the **MCP Inspector**.

### 🤖 Option 1: Claude Desktop (Recommended)

1. Open your Claude Desktop configuration file:
    - **Mac:** `~/Library/Application Support/Claude/claude_desktop_config.json`
    - **Windows:** `%APPDATA%\Claude\claude_desktop_config.json`

2. Add the following configuration:
```json
{
  "mcpServers": {
    "embabel-file-manager": {
      "command": "npx",
      "args": [
        "-y",
        "mcp-remote",
        "http://localhost:8080/sse"
      ]
    }
  }
}
```

3. Restart Claude Desktop. You should see a connection icon.

4. **Ask Claude:** "Retrieve the list of Confluence spaces"

### 🔍 Option 2: MCP Inspector

If you want to debug the tools manually:

1. Ensure the Spring Boot app is running.
2. Run the inspector in your terminal:
   ```bash
   npx @modelcontextprotocol/inspector
   ```
3. In the browser window that opens:
    - Select **SSE**.
    - Enter URL: `http://localhost:8080/sse`.
    - Click **Connect** and test the tools via the UI.