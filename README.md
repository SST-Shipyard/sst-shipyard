# Snap Ships Tactics Shipyard
SST Shipyard is a web app running purely in the browser
(that is, there's on connection to any server after downloading the page)
that helps you play the board game [Snap Ships Tactics](https://www.snapshipstactics.com/).

At the moment the shipyard lets you build, view and share your squad.
Later it will help you also generate AI battles (that is, solo or co-op),
keep track of game state while playing, and handle custom cards.

See the app in action at https://sst-shipyard.github.io.

## Discussion, feedback, ideas etc.
SST Shipyard has a thread in the [Snap Ships Discord](https://discord.com/channels/755085914195361832/1142059794405015573).
You can also leave feature requests and bug reports here, in the Github issue tracker.

## Building
Run

  npm install
  npx shadow-cljs release main

and the files needed to run the app will be under the directory `public`.

## License
The software is licensed under the MIT license.
The game assets are not under this license and are copyrighted by Snap Ships LLC.
