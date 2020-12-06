import { NgModule } from '@angular/core';

import { NoteModule } from './note/note.module';

@NgModule({
  declarations: [],
  imports: [NoteModule],
  exports: [NoteModule],
})
export class ModulesModule {}
