# Wikipedia Game Solver

Wikipedia Game Solver is a Java program that solves the “Wikipedia game.”

Players begin on one Wikipedia article and attempt to reach another article by only clicking hyperlinks between pages. The challenge may involve reaching the target page in the fewest number of clicks, or faster than another player.

This project is currently a work in progress.

## Version 0.2

### Features

* Implemented semantic article scoring using Deep Java Library embeddings
* Replaced pure BFS traversal with heuristic-guided graph search
* Added embedding and article-content caching
* Added target-page preprocessing and caching
* Added reverse-link analysis using Wikipedia backlinks
* Improved hyperlink extraction using Jsoup
* Filtered navigation, reference, category, and external-link sections from candidate links

### Current Implementation

The search algorithm now uses semantic embeddings to estimate how closely a candidate article relates to the destination article.

Instead of exploring pages strictly by depth as in a traditional Breadth-First Search, candidate nodes are assigned heuristic scores based on their semantic similarity to the body of the target article. This allows the search to prioritize more promising paths while still retaining the ability to revisit alternative branches when necessary.

To reduce redundant computation, article embeddings and target-page data are cached and reused throughout the search process.

Hyperlink extraction was also redesigned. The original implementation relied on the Wikipedia API's complete link list for a page, which included links from references, navigation boxes, categories, and external-link sections. The updated implementation uses Jsoup to parse article content directly and extracts only links that appear within the main article body. This significantly reduces graph noise and prevents the search from following links that would not be valid in the Wikipedia game.

Additional heuristics were introduced using Wikipedia backlink data. Pages that link directly to the target article are identified once at the start of the search and used as indicators that a candidate path may be approaching the destination.

These changes substantially improve search quality while reducing the number of unnecessary page expansions and API requests.

## Version 0.1

### Features

* Added command-line input
* Added Wikipedia API integration using JWiki
* Created `WikiService` utility class
* Implemented initial graph search algorithm
* Implemented `Node` state class

### Current Implementation

The current version can:

* connect to the Wikipedia API,
* retrieve article hyperlinks,
* organize articles into a graph structure,
* and traverse this graph to search for a path between articles.

The original implementation used a pure Breadth-First Search (BFS) algorithm. However, Wikipedia forms an extremely large graph with a high branching factor, causing the search space to expand combinatorially.

Large numbers of API requests also trigger throttling from Wikipedia unless delays are introduced between requests, which significantly increases runtime.

## Future Plans

* Heuristic-based search (A*)
* Semantic or AI-assisted pathfinding with Deep Java Library
* Improved category analysis
* Graph optimization and caching
* User interface
* Performance improvements and multithreading
