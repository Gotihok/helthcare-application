import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {TestPage} from './test/test.page/test.page';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, TestPage],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
}
