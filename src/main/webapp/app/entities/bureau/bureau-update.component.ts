import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IBureau } from 'app/shared/model/bureau.model';
import { BureauService } from './bureau.service';

@Component({
    selector: 'jhi-bureau-update',
    templateUrl: './bureau-update.component.html'
})
export class BureauUpdateComponent implements OnInit {
    private _bureau: IBureau;
    isSaving: boolean;

    constructor(private bureauService: BureauService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ bureau }) => {
            this.bureau = bureau;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.bureau.id !== undefined) {
            this.subscribeToSaveResponse(this.bureauService.update(this.bureau));
        } else {
            this.subscribeToSaveResponse(this.bureauService.create(this.bureau));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IBureau>>) {
        result.subscribe((res: HttpResponse<IBureau>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get bureau() {
        return this._bureau;
    }

    set bureau(bureau: IBureau) {
        this._bureau = bureau;
    }
}
