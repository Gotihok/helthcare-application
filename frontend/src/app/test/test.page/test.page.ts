import { Component } from '@angular/core';
import {TestService} from '../services/test.service';

@Component({
  selector: 'app-test',
  imports: [],
  templateUrl: './test.page.html',
  styleUrl: './test.page.css',
})
export class TestPage {
  constructor(
    private testService: TestService,
  ) {}

  protected buttonClicked() {
    this.testService.callBackend().subscribe({
      next: (e: Event) => {
        console.log("NEXT PATH");
        console.log(e);
      },
      error: (e: Event) => {
        console.log("ERROR PATH");
        console.log(e);
      }
    });
  }
}
