# Wikipedia Game Solver

Wikipedia Game Solver is a Java program that solves the “Wikipedia game.”

Players begin on one Wikipedia article and attempt to reach another article by only clicking hyperlinks between pages. The challenge may involve reaching the target page in the fewest number of clicks, or faster than another player.

This project is currently a work in progress.

## Version 0.1

### Features

* Added command-line input
* Added Wikipedia API integration using JWiki
* Created `WikiService` utility class
* Implemented initial graph search algorithm
* Implemented `Node` state class

## Current Implementation

The current version can:

* connect to the Wikipedia API with JWiki,
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
