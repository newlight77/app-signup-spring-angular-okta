import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NoteListComponent } from './note-list/note-list.component';
import { NoteEditComponent } from './note-edit/note-edit.component';
import { NoteService } from './note-service/note.service';
import { NoteRoutingModule } from './note-routing.module';
import { MaterialModule } from 'src/app/shared/material.module';

@NgModule({
  imports: [CommonModule, FormsModule, MaterialModule, NoteRoutingModule],
  declarations: [NoteListComponent, NoteEditComponent],
  providers: [NoteService],
  exports: [],
})
export class NoteModule {}
