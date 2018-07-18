import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IBureau } from 'app/shared/model/bureau.model';
import { Principal } from 'app/core';
import { BureauService } from './bureau.service';

@Component({
    selector: 'jhi-bureau',
    templateUrl: './bureau.component.html'
})
export class BureauComponent implements OnInit, OnDestroy {
    bureaus: IBureau[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private bureauService: BureauService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.bureauService.query().subscribe(
            (res: HttpResponse<IBureau[]>) => {
                this.bureaus = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInBureaus();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IBureau) {
        return item.id;
    }

    registerChangeInBureaus() {
        this.eventSubscriber = this.eventManager.subscribe('bureauListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
