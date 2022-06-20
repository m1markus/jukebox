import { Component } from '@angular/core';

import { SongListItemComponent } from './song-list-item/song-list-item.component';
import { Song } from './shared/song'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.sass']
})
export class AppComponent {
  title = 'ui-jukebox-angular';

  songs: Song[]

  constructor() { 
    this.songs = [
      new Song('01', "It's my life"),
      new Song('123', 'this is my song'),
      new Song('345', 'this is her song')
    ]
  }
}
