import { Component, Input, OnInit } from '@angular/core';

import { Song } from '../shared/song'

@Component({
  selector: 'app-song-list',
  templateUrl: './song-list.component.html',
  styleUrls: ['./song-list.component.sass']
})
export class SongListComponent implements OnInit {

  @Input() songs: Song[];

  constructor() { 
    this.songs = [];
  }

  ngOnInit(): void {
  }

  clickAction1(): void {
    console.log('button action-1 pressed')
  }

  clickAction2(): void {
    console.log('button action-2 pressed')
  }

}
