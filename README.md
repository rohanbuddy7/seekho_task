# Seekho Task
### A simple Android app that uses the Jikan API to fetch and display a list of anime series, allowing users to view details and trailers.

## Limitations
- Offline caching is implemented.  
- Trailer URLs from the API are broken, so posters are shown instead, with a button added below to complete the layout.  
- main_cast is not available in the API, so studios are displayed instead.  
- Paging is not yet implemented.

## Features
- Fetches anime list from the Jikan API  
- Offline architecture using Room  
- Anime detail screen on item click  
- Error handling implemented  
- Built using Jetpack Compose, Navigation

