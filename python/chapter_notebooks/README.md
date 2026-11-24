# Agentic Design Patterns: Chapter Notebooks

**The notebooks in this directory originated from the GitHub repository [evoiz/Agentic-Design-Patterns](https://github.com/evoiz/Agentic-Design-Patterns). Only notebooks used for this project were retained; this is not a complete copy of the upstream collection. Most of the retained notebooks have been modified from their original versions.**

These Python/Jupyter materials accompany *Agentic Design Patterns: A Hands-On Guide to Building Intelligent Systems* by Antonio Gulli. The descriptions below summarize the local notebook collection.

## Project direction

This project is a study collection focused on understanding the book's patterns through LangChain and OpenAI. The intended final collection will retain the LangChain examples and adapt them to include direct implementations using the official OpenAI Python SDK. Where applicable, the LangChain versions will also use OpenAI models.

The paired implementations are intended to make the same pattern easier to follow: first through LangChain's abstractions, then through explicit API calls and ordinary Python control flow. The [Chapter 2 routing notebook](Chapter_02_Routing_langchain_openai.ipynb) already includes both approaches.

This conversion is in progress. Examples using other frameworks and providers remain in the directory and inventory below until they are reviewed and removed individually. Their presence describes the current collection, rather than the intended final scope; not every notebook has been converted to OpenAI.

## What the notebooks cover

Files use chapter numbers and variant names to identify the subject and framework. The current inventory still includes implementations using Google ADK, LangChain, LangGraph, and CrewAI while the collection is being narrowed to the project direction above.

This directory currently contains 56 notebooks with content and no empty placeholder notebooks. Some are short snippets or companion setup files, including two MCP initialization notebooks. This inventory will be updated as examples are reviewed, adapted, or removed.

| Chapter | Topic | Contents and variants |
| --- | --- | --- |
| 1 | Prompt chaining | Passing results through successive steps, with a code example and a JSON example. |
| 2 | Routing | Classifying requests and selecting simulated handlers in [Chapter_02_Routing_langchain_openai.ipynb](Chapter_02_Routing_langchain_openai.ipynb), with LangChain and direct OpenAI Python SDK implementations. The Google ADK and OpenRouter examples have been removed. See the [routing notes](notes/Chapter_02_Routing_langchain_openai_notes.md) for explanations. |
| 3 | Parallelization | Organizing independent work to run concurrently with Google ADK and LangChain. |
| 4 | Reflection | Reviewing generated work and refining it through feedback; ADK, LangChain, and iterative-loop examples. |
| 5 | Tool use | Connecting agents to callable functions, code execution, Google Search, and Vertex AI Search; includes CrewAI and LangChain examples. |
| 6 | Planning | Structuring work into tasks and producing research reports; a planning code example and a Deep Research API example. |
| 7 | Multiple agents | Coordinating agents through sequential steps, parallel tasks, loops, a coordinator, and agents exposed as tools; includes ADK/Gemini and CrewAI/Gemini variants. |
| 8 | Memory | Managing conversation history, session data, explicit state updates, saved agent outputs, and in-memory recall with ADK, LangChain, and LangGraph. |
| 9 | Adaptation | An OpenEvolve-oriented example introducing improvement through an evolutionary approach. |
| 10 | Model Context Protocol (MCP) | Connecting agents to MCP tools, including FastMCP server/client examples and filesystem access. Separate initialization notebooks accompany some examples. |
| 11 | Goals and progress | An iterative example that works toward an objective and checks progress along the way. |
| 12 | Recovery | Using a fallback when the preferred operation fails. |
| 13 | Human involvement | A customer-support example that incorporates human participation in an agent workflow. |
| 14 | Retrieval-augmented generation (RAG) | Supplying retrieved information to an agent through LangChain, Google Search, and Vertex AI examples. |
| 15 | Agent communication | A2A interactions, a weather agent's AgentCard, and examples of synchronous and streaming requests. |
| 16 | Resource optimization | Code snippets and a Google Search example exploring how agents can account for available resources. |
| 17 | Reasoning | Examples involving stepwise prompting, code execution, Google DeepSearch, and revising an answer after checking it. |
| 18 | Guardrails | Validating tool calls, using a model to screen interactions, and applying practical checks to agent behavior. |
| 19 | Evaluation | Assessing responses with basic evaluation logic and model-based judging. |
| 20 | Prioritization | A SuperSimplePM example focused on deciding which tasks deserve attention first. |
| 21 | Exploration and discovery | An Agent Laboratory example illustrating an agent-assisted research workflow. |

## Appendix materials

- **Appendix C code:** `Appendix_C_(Code).ipynb` contains illustrative framework code.
- **Pydantic supplement:** `Appendix_Pydantic.ipynb` contains a small example of defining structured data with Pydantic.

The collection includes short snippets and setup cells; the presence of a notebook does not establish that it is a complete, independently runnable application.

## Using the examples

Open the desired `.ipynb` file in Jupyter Notebook, JupyterLab, or another notebook editor. Read its setup and code cells before running it, install the dependencies used by that example, and configure any required provider credentials or cloud resources. Requirements vary by framework and service. These notebooks have not been executed or validated as part of this documentation update.

For notebooks containing both implementations, read and run each section separately to compare how it performs classification, calls tools or handlers, and returns results. Running both versions can make separate API requests for the same examples. OpenAI examples use `OPENAI_API_KEY`; follow each notebook's configuration instructions, including any local `.env` setup.

The [notes folder](notes/README.md) contains Codex-generated explanations of selected notebooks, including construction and execution walkthroughs and alternative implementations. Python code generated by Codex is identified in comment headers, with original author and license notices preserved.

## Attribution and license

The accompanying [LICENSE](LICENSE) supplies the MIT terms for the code examples.

Retain existing copyright and attribution notices, including those crediting Marco Fago and Mahtab Syed in individual notebooks. The MIT license for code examples does not relicense the book's prose, manuscript, or other separately copyrighted material.
