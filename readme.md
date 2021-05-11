# Cleanurge

[![Contributors](https://img.shields.io/github/contributors/dsckgec/cleanurge-app.svg)](https://github.com/dsckgec/cleanurge-app/graphs/contributors) [![Forks](https://img.shields.io/github/forks/dsckgec/cleanurge-app.svg)](https://github.com/dsckgec/cleanurge-app/network/members) [![Issues](https://img.shields.io/github/issues/dsckgec/cleanurge-app.svg)](https://github.com/dsckgec/cleanurge-app/issues) [![Pull Request](https://img.shields.io/github/issues-pr-closed-raw/dsckgec/cleanurge-app)](https://github.com/dsckgec/cleanurge-app/pulls)


A scalable waste management system powered by IoT.

## There are 3 repositories for the entire cleanurge system in total
1. **[cleanurge-mcu](https://github.com/DSCKGEC/cleanurge-mcu):** Containing the source code for the microcontroller
2. **[cleanurge-backend](https://github.com/DSCKGEC/cleanurge-backend):** Containing the source code for the Express backend
3. **[cleanurge-app](https://github.com/DSCKGEC/cleanurge-app):**  Containing the source code for the Android App

# CleanUrge App
This is the repo for the app part of project Cleanurge

## Contents

1. [Description](#description)
2. [Project roadmap](#project-roadmap)
3. [Getting started](#getting-started)
4. [How the app looks like](#how-the-app-looks-like)
5. [Built with](#built-with)
6. [Current Issues](#current-issues)
7. [Contributing](#contributing)
8. [Authors](#authors)
9. [License](#license)
10. [Acknowledgments](#acknowledgments)

## Description

### What's the problem?
Today, waste is a significant global issue. Increasing volumes of waste are being generated as the global population and living standards rise.
The environmental impact is significant, with massive volumes of waste generated annually with only basic or little treatment to minimise its impact. People are increasingly concerned about the production of waste and its effect, and are seeking ways to deal with the problem.

### How can this project help?
Our project serves to prevent overaccumulation of waste at public bins by maintaining a log of their waste accumulation levels. The authorities will have the feature to get live status of the various public bins and appoint garbagemen to clear off any bins on overaccumulation. Moreover, the local residents will be able to report if there is an urgent attention needed in case of an overflow outside the garbage bins or excess accumulation of wastes nearby.

### The idea
The idea is to have wireless beacons configured at various public bins to detect and measure the level of waste accumulation at the bins. When the level at any of the bins cross a threshold, a request is triggered to a web server that is then used to send notifications to the authorities in an app as well as web based software. The mobile application also supports the feature for individuals living in the locality to report of any waste accumulation directly to the authorities.

## Project roadmap

The project currently does the following things.

- The self-reported issues are visible with image, address and time
- The beacon information is visible with exact map location
- User can add new issue with report title, image and address
- User can track resolved and unresolved issues

See below for our future steps.

- Showing self-reported issues with exact location
- Adding recycler pagination
- Real-time data fetching
- Real-time network status checking
- Bug Fixes
- UI upgrades
- and lot more...

## Getting started

Everyone is welcomed to contribute to our project. Mentioning in bold, you do not need to know the tech stack and tools beforehand to be a part of our project. This is a learn-and-build projects where the contributors build alongside learning the various concepts and technologies involved.
Below are a few prerequisites and installation guides:

### Prerequisites

1. Android Development using JAVA 8
2. Working with API
3. Using Firebase

#### Softwares needed

-   [Android Studio 4.1](https://developer.android.com/studio)
-   [Latest JDK](https://www.oracle.com/java/technologies/javase-jdk16-downloads.html)
-   [Postman](https://www.postman.com/downloads/)

#### Knowledge needed

The best way to learn the following is to google each and everything!

-   Very basic understanding of git and github:

    -   What are repositories (local - remote - upstream), issues, pull requests
    -   How to clone a repository, how to fork a repository, how to set upstreams
    -   Adding, committing, pulling, pushing changes to remote repositories
   
-   Android:

    -   [Android Development with JAVA](https://developer.android.com/codelabs/build-your-first-android-app#0)
    -   [Retrofit for API integration](https://square.github.io/retrofit/)

-   Postman:
 
    -   [Testing API](https://learning.postman.com/docs/getting-started/introduction/)
    -   You'll find all the API endpoints used in this project here -> https://cleanurge.herokuapp.com/docs/

## Installing

A step by step series of instructions that tell you how to get the project running locally is given below. Google every issue you face following the below instructions or just ask us in our Discord / WhatsApp group.

- Fork and clone the repository.
- Open Android Studio --> New --> Project from Version Control... ---> Paste the repo url ---> Clone
- Wait for the gradle sync to finish and click the play button to install the app on your local device / emulator.

## How the app looks like

<img src="https://user-images.githubusercontent.com/73014152/117626235-833cad00-b194-11eb-97b6-331d12ebc238.jpg" width = "210" height = "450">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<img src="https://user-images.githubusercontent.com/73014152/117626245-86379d80-b194-11eb-8e58-1bc309b9353a.jpg" width = "210" height = "450">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<img src="https://user-images.githubusercontent.com/73014152/117626258-88016100-b194-11eb-8ed0-cab345a615fc.jpg" width = "210" height = "450">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<img src="https://user-images.githubusercontent.com/73014152/117626265-8a63bb00-b194-11eb-9eeb-3498a8349c60.jpg" width = "210" height = "450">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<img src="https://user-images.githubusercontent.com/73014152/117626280-8cc61500-b194-11eb-9cee-6274e6530c5b.jpg" width = "210" height = "450">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<img src="https://user-images.githubusercontent.com/73014152/117626287-8e8fd880-b194-11eb-9cc4-e1e239933bcb.jpg" width = "210" height = "450">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<img src="https://user-images.githubusercontent.com/73014152/117626294-90599c00-b194-11eb-9139-ec322f28c73f.jpg" width = "210" height = "450">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<img src="https://user-images.githubusercontent.com/73014152/117626301-93548c80-b194-11eb-96b9-acec506a22a3.jpg" width = "210" height = "450">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;


## Built with

- [JAVA](https://www.oracle.com/in/java/)
- [Android Studio 4.1](https://developer.android.com/studio)
- [Retrofit](https://square.github.io/retrofit/)
- [Firebase](https://firebase.google.com/docs/storage/android/start)

## Current Issues

- [Improve the layout of Login and CreateAccount screen](https://github.com/DSCKGEC/cleanurge-app/issues/7) 
- [Merge all the Registration Activities in a single activity](https://github.com/DSCKGEC/cleanurge-app/issues/8)
- [Add a filter in 'HomeFragment' to filter out the reported issues](https://github.com/DSCKGEC/cleanurge-app/issues/9)
- [Wrap all the EditTexts in the app with TextInputLayout](https://github.com/DSCKGEC/cleanurge-app/issues/10)

## Contributing

Please read [contributing.md](contributing.md) for details on our code of conduct, and the process for submitting pull requests to us.

## Authors

<a href="https://github.com/DSCKGEC/cleanurge-app/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=DSCKGEC/cleanurge-app" />
</a>

## License

This project is licensed under the GNU Public License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

[contributors-img](https://contrib.rocks)
