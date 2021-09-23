import { Component, Input, OnInit } from '@angular/core';
import { BsModalRef } from 'ngx-bootstrap/modal';
import { modalConfigDefaults } from 'ngx-bootstrap/modal/modal-options.class';

@Component({
  selector: 'app-alerts',
  templateUrl: './alerts.component.html',
  styleUrls: ['./alerts.component.css']
})
export class AlertsComponent implements OnInit {

  @Input() message: string;
  @Input() type: string = 'success';

  constructor(
    public modal: BsModalRef
  ) { }

  ngOnInit(): void {
    window.setTimeout(()=>{
      this.onClose()
    }, 2000)
  }

  onClose() {
    this.modal.hide();
  }

}
